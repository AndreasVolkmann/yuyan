package me.avo.anki.service

class AnkiViaDatabase : AnkiClient {
    override suspend fun execute(ankiRequest: AnkiRequest) {
        TODO("Not yet implemented")
    }

    override suspend fun <TResponse : Any> execute(typedAnkiRequest: TypedAnkiRequest<TResponse>): TResponse {
        TODO("Not yet implemented")
    }

}