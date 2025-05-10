package me.avo.jugen

import me.avo.Chat
import me.avo.messages.ChatMessage
import me.avo.model.LargeLanguageModel
import me.avo.model.ModelOptions

class SentenceGenerator(val languageModel: LargeLanguageModel) {

    suspend fun generate(word: String): String {
        val chat = Chat(
            mutableListOf(getMessage(word)),
            ModelOptions(maxTokens = 150)
        )

        val result = languageModel.execute(chat)
        return result
    }

    private fun getMessage(word: String): ChatMessage = ChatMessage(
        "user",
        """
        |Generate a single sentence for learning purposes with the word $word.
        |No other response
        """.trimMargin()
    )
}