package me.avo.model

import com.azure.ai.openai.OpenAIClientBuilder
import com.azure.ai.openai.models.*
import com.azure.core.credential.AzureKeyCredential
import kotlinx.coroutines.reactor.awaitSingle
import me.avo.Chat
import me.avo.messages.ChatMessage
import kotlin.time.measureTimedValue

class AzureOpenAiModel(val apiKey: String, val host: String) : LargeLanguageModel {
    override suspend fun execute(chat: Chat): String {
        val client = OpenAIClientBuilder()
            .credential(AzureKeyCredential(apiKey))
            .endpoint("https://$host.openai.azure.com/")
            .buildAsyncClient()

        val options = getOptions(chat)
        println("(LLM): ${chat.modelOptions}, ${chat.messages.size} messages")
        val completions = measureTimedValue {
            val response = client.getChatCompletions(chat.modelOptions.model, options)
            response.awaitSingle()
        }

        println("(LLM): Time taken: ${completions.duration.toIsoString()}")
        completions.value.usage?.let { println("(LLM): Usage: Total: ${it.totalTokens}, Completion: ${it.completionTokens}, Prompt: ${it.promptTokens}") }
        
        return completions.value.choices.joinToString { it.message.content }
    }

    private fun getOptions(chat: Chat): ChatCompletionsOptions {
        val options = chat.modelOptions
        val messages = chat.messages.map(::mapMessage)
        return ChatCompletionsOptions(messages)
            .setMaxTokens(options.maxTokens)
            .setTemperature(options.temperature)
            .setTopP(options.topP)
    }

    private fun mapMessage(it: ChatMessage) = when (it.role) {
        "user" -> ChatRequestUserMessage(it.content)
        "assistant" -> ChatRequestAssistantMessage(it.content)
        "system" -> ChatRequestSystemMessage(it.content)
        else -> ChatRequestUserMessage(it.content)
    }
}