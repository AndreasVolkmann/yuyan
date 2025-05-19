package me.avo.anki.service

import me.avo.anki.AnkiCard

class AnkiRequestFactory {

    fun getDeckNamesRequest() = AnkiRequest(
        action = "deckNames",
        params = null
    ).withResponseType<List<String>>()

    fun findCards(query: String) = AnkiRequest(
        "findCards",
        AnkiParams(query = query)
    ).withResponseType<List<Long>>()

    fun getCards(cardIds: List<Long>) = AnkiRequest(
        "cardsInfo",
        AnkiParams(cards = cardIds)
    ).withResponseType<List<AnkiCard>>()

    fun updateCard(card: AnkiCard, key: String, value: String) = AnkiRequest(
        "setSpecificValueOfCard",
        AnkiParams(
            card = card.cardId,
            keys = listOf(key),
            newValues = listOf(value)
        )
    ).withResponseType<List<Boolean>>()

    fun cardsToNotes(cardId: Long) = AnkiRequest(
        action = "cardsToNotes",
        params = AnkiParams(cards = listOf(cardId))
    ).withResponseType<List<Long>>()

    fun updateNoteFields(id: Long, key: String, value: String) = AnkiRequest(
        action = "updateNoteFields",
        params = AnkiParams(
            note = Note(
                id, fields = mapOf(key to value)
            )
        )
    )
}