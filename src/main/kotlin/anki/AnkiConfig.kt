package me.avo.anki

data class AnkiConfig(
    val url: String = "http://localhost:8765",
    val deckName: String
) {
}