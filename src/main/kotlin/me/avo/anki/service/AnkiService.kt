package me.avo.anki.service

import me.avo.anki.AnkiCard
import me.avo.anki.AnkiConfig
import me.avo.anki.util.UnicodeEscapeUtils

class AnkiService(
    private val config: AnkiConfig,
    private val client: AnkiClient,
    private val factory: AnkiRequestFactory,
) {
    suspend fun findDifficultCards() = findCards("rated:5:2")

    suspend fun findCardsWithoutSampleSentence() = findCards("${config.sampleSentenceFieldName}:")

    suspend fun updateCardSampleSentence(noteId: Long, sampleSentence: String) {
        val request = factory.updateNoteFields(noteId, config.sampleSentenceFieldName, sampleSentence)
        client.execute(request)
    }

    suspend fun updateCardAudio(noteId: Long, audioFilePath: String, fieldName: String, fileName: String? = null) {
        val storedFileName = client.execute(factory.storeMediaFile(audioFilePath, fileName))
        val soundName = UnicodeEscapeUtils.unescapeUnicode(storedFileName.trim('"'))
        val audioTag = "[sound:$soundName]"
        val request = factory.updateNoteFields(noteId, fieldName, audioTag)
        client.execute(request)
    }
    
    suspend fun findCardNoteIds(card: AnkiCard): List<Long> {
        val request = factory.cardsToNotes(card.cardId)
        return client.execute(request)
    }
    
    private suspend fun findCards(query: String): List<AnkiCard> {
        val cardIds = findCardIds("deck:${config.deckName} $query")
        return client.execute(factory.getCards(cardIds))
    }

    private suspend fun findCardIds(query: String): List<Long> = client.execute(factory.findCards(query))
}