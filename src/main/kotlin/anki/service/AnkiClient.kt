package me.avo.anki.service

interface AnkiClient {

    suspend fun execute(ankiRequest: AnkiRequest)

    suspend fun <TResponse : Any> execute(
        typedAnkiRequest: TypedAnkiRequest<TResponse>,
    ): TResponse
}