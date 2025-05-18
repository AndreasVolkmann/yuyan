package me.avo.anki

import kotlinx.serialization.Serializable

@Serializable
data class AnkiRequest(
    val action: String,
    val params: AnkiParams? = null,
    val version: Int = 6
) {
    @Serializable
    data class AnkiParams(
        val note: Note? = null,
        val deckName: String? = null,
        val modelName: String? = null,
        val tags: List<String>? = null,
        val query: String? = null,
        val cards: List<Long>? = null,
    )

    @Serializable
    data class Note(
        val modelName: String,
        val fields: Map<String, String>,
        val tags: List<String>,
        val options: Options,
    )

    @Serializable
    data class Options(
        val allowDuplicate: Boolean = false,
    )
}