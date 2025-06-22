package me.avo.anki.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.avo.anki.AnkiConfig
import java.net.ConnectException

class AnkiViaHttpClient(
    val config: AnkiConfig,
) : AnkiClient {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
    }

    override suspend fun <TResponse : Any> execute(
        typedAnkiRequest: TypedAnkiRequest<TResponse>,
    ): TResponse {
        val response = executeRequest(typedAnkiRequest.request)
        return response.body(typedAnkiRequest.typeInfo)
    }

    override suspend fun execute(ankiRequest: AnkiRequest) {
        executeRequest(ankiRequest)
    }

    private suspend fun executeRequest(ankiRequest: AnkiRequest): HttpResponse = try {
        client.post(config.url) {
            contentType(ContentType.Application.Json)
            setBody(ankiRequest)
        }.also(::println)
    } catch (ex: ConnectException) {
        throw RuntimeException(
            "Failed to connect to Anki server at ${config.url}. " +
                    "Please ensure the AnkiConnect plugin is installed and running.",
            ex
        )
    }
}