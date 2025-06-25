package me.avo.llm.model

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.avo.Chat
import me.avo.llm.messages.RequestBody
import me.avo.llm.messages.ResponseBody

class AzureAiModel(val config: ModelConfig) : LargeLanguageModel {
    private val url = if (config.isServiceEndpoint) "https://${config.host}/models/chat/completions?api-version=2024-05-01-preview"
    else "https://${config.host}/v1/chat/completions"

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(HttpRequestRetry) {
            retryIf { _, response -> 
                response.status == HttpStatusCode.TooManyRequests 
            }
            exponentialDelay(base = 5.0, maxDelayMs = 30000)
            modifyRequest { request ->
                println("Retrying request: ${request.url}")
                request.headers.append("X-Retry-Count", retryCount.toString())
            }
        }
    }

    override suspend fun execute(chat: Chat): String {
        val response = client.post(url) {
            header(HttpHeaders.Authorization, "Bearer ${config.apiKey}")
            contentType(ContentType.Application.Json)
            setBody(constructRequestBody(chat))
        }
        println(response)
        
        if (response.status.isSuccess()) {
            val responseBody: ResponseBody = response.body()
            return responseBody.choices.joinToString { it.message.content }
        }
        else {
            throw Exception("Failed to get response: ${response.status.value} - ${response.status.description}, ${response.bodyAsText()}")
        }
    }

    private fun constructRequestBody(chat: Chat): RequestBody {
        val modelOptions = chat.modelOptions
        return RequestBody(
            chat.messages,
            modelOptions.maxTokens,
            modelOptions.temperature.toFloat(),
            modelOptions.topP.toFloat(),
            model = if (config.isServiceEndpoint) config.id else null
        )
    }
}