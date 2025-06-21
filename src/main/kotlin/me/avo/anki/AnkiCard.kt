package me.avo.anki

import kotlinx.serialization.Serializable

@Serializable
data class AnkiCard(
    val cardId: Long,
    val interval: Int,
    val ord: Int,
    val due: Int,
    val reps: Int,
    val lapses: Int,
    val left: Int,
    val fields: Map<String, CardField>,
) {
}


@Serializable 
data class CardField(
    val value: String,
    val order: Int
)