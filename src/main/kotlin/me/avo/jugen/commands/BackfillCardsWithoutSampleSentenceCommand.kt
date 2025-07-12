package me.avo.jugen.commands

import me.avo.Command
import me.avo.anki.AnkiCard
import me.avo.anki.AnkiConfig
import me.avo.anki.service.AnkiService
import me.avo.jugen.Jugen

class BackfillCardsWithoutSampleSentenceCommand(
    private val ankiService: AnkiService,
    private val jugen: Jugen,
    private val ankiConfig: AnkiConfig
) : Command {
    override val id = "backfill"

    override suspend fun execute(arguments: List<String>) = ankiService
        .findCardsWithoutSampleSentence()
        .also { println("Cards without sample sentence: ${it.size}") }
        .take(1)
        .onEach(::println)
        .forEach { process(it) }

    private suspend fun process(card: AnkiCard) {
        card.fields[ankiConfig.targetFieldName]?.value
            ?.let { backfill(it, card) }
            ?: println("No target term found for card: ${card.cardId}")
    }

    private suspend fun backfill(targetTerm: String, card: AnkiCard) {
        val (sentence, audioFile) = jugen.generateSentence(targetTerm)
        val noteIds = ankiService.findCardNoteIds(card)
        val noteId = noteIds.firstOrNull()
        if (noteId != null) {
            ankiService.updateCardSampleSentence(noteId, sentence)
            ankiService.updateCardAudio(noteId, audioFile.absolutePath, ankiConfig.sentenceAudioFieldName)
        }
    }
}