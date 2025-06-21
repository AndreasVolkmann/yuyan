package me.avo.jugen

import me.avo.anki.AnkiCard
import me.avo.anki.AnkiConfig
import me.avo.anki.service.AnkiService

class BackfillCardsWithoutSampleSentenceCommand(
    private val ankiService: AnkiService,
    private val jugen: Jugen,
    private val ankiConfig: AnkiConfig
) {
    suspend fun anki() = ankiService
        .findCardsWithoutSampleSentence()
        .also { println("Cars without sample sentence: ${it.size}") }
        .take(10)
        .onEach(::println)
        .forEach { process(it) }

    private suspend fun process(card: AnkiCard) {
        val targetTerm = card.fields[ankiConfig.targetFieldName]?.value
        if (targetTerm != null) {
            val (sentence, audioFile) = jugen.generateSentence(targetTerm)
            val noteIds = ankiService.findCardNoteIds(card)
            val noteId = noteIds.firstOrNull()
            if (noteId != null) {
                ankiService.updateCardSampleSentence(noteId, sentence)
                ankiService.updateCardAudio(noteId, audioFile.absolutePath, "SentenceAudio")
            }
        } else {
            println("No target term found for card: ${card.cardId}")
        }
    }
}