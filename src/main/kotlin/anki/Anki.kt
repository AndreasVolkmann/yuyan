package me.avo.anki

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Anki(
    private val config: AnkiConfig,
) {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
    }

    suspend fun findDifficultCards(): List<AnkiCard> {
        val cardIds = findCards("deck:${config.deckName} rated:7:2")
        return getCards(cardIds)
    }

    suspend fun getCards(cardIds: List<Long>) = sendAction<List<AnkiCard>>(
        AnkiRequest(
            "cardsInfo",
            AnkiRequest.AnkiParams(cards = cardIds)
        )
    )

    suspend fun getDeckNames() = sendAction<List<String>>(
        AnkiRequest("deckNames", null))

    suspend fun findCards(query: String) = sendAction<List<Long>>(
        AnkiRequest(
            "findCards",
            AnkiRequest.AnkiParams(query = query)
        )
    )

    private suspend inline fun <reified TResponse> sendAction(request: AnkiRequest): TResponse {
        val response = client.post(config.url) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        println(response)
        return response.body<TResponse>()
    }
}