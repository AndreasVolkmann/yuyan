package me.avo.model

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.avo.Chat
import me.avo.messages.RequestBody
import me.avo.messages.ResponseBody

class AzureAiModel(val config: ModelConfig) : LargeLanguageModel {
    private val url = "https://${config.host}/v1/chat/completions"

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    override suspend fun execute(chat: Chat): String {
        val response = client.post(url) {
            header(HttpHeaders.Authorization, "Bearer ${config.apiKey}")
            contentType(ContentType.Application.Json)
            setBody(constructRequestBody(chat))
        }.body<ResponseBody>()
        return response.choices.joinToString { it.message.content }
    }

    private fun constructRequestBody(chat: Chat): RequestBody {
        val modelOptions = chat.modelOptions
        return RequestBody(
            chat.messages,
            modelOptions.maxTokens,
            modelOptions.temperature.toFloat(),
            modelOptions.topP.toFloat()
        )
    }
}