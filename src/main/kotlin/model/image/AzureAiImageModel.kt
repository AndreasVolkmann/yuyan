package me.avo.model.image

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import me.avo.model.ModelConfig

class AzureAiImageModel(
    val config: ModelConfig
) : ImageModel {
    private val url = "https://${config.host}"

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

    override suspend fun generateImage(prompt: String, options: ImageOptions): ImageResponse {
        val response = client.post(url) {
            header(HttpHeaders.Authorization, "Bearer ${config.apiKey}")
            contentType(ContentType.Application.Json)
            setBody(ImageRequest(
                model = "dall-e-3",
                prompt = prompt,
                size = options.size,
                style = options.style,
                quality = options.quality,
                n = options.n
            ))
        }
        println(response)

        if (response.status.isSuccess()) {
            return response.body()
        } else {
            throw Exception("Failed to generate image: ${response.status.value} - ${response.status.description}, ${response.bodyAsText()}")
        }
    }
}

@Serializable
data class ImageRequest(
    val model: String,
    val prompt: String,
    val size: String = "1024x1024",
    val style: String = "vivid",
    val quality: String = "standard",
    val n: Int = 1
)

@Serializable
data class ImageOptions(
    val size: String = "1024x1024",
    val style: String = "vivid",
    val quality: String = "standard",
    val n: Int = 1
)

@Serializable
data class ImageResponse(
    val created: Long,
    val data: List<ImageData>
)

@Serializable
data class ImageData(
    val url: String,
    val revised_prompt: String? = null
)