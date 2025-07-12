package me.avo.anki

data class AnkiConfig(
    val url: String = "http://localhost:8765",
    val deckName: String,
    val targetFieldName: String = "Simplified",
    val sampleSentenceFieldName: String = "SentenceSimplified",
    val sentenceAudioFieldName: String = "SentenceAudio",
) {
}