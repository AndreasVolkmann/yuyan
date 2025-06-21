package me.avo.jugen

import me.avo.Chat
import me.avo.llm.messages.ChatMessage
import me.avo.llm.model.LargeLanguageModel
import me.avo.llm.model.ModelOptions

class SentenceGenerator(val languageModel: LargeLanguageModel, val config: JugenConfig) {

    suspend fun generate(word: String): String {
        val chat = Chat(
            mutableListOf(getMessage(word)),
            ModelOptions(maxTokens = 150)
        )

        val result = languageModel.execute(chat)
        return result.trim('“', '”')
    }

    private fun getMessage(word: String): ChatMessage = ChatMessage(
        "user",
        """
        |Generate a single sentence in ${config.language.name} for learning purposes with the word $word.
        |Follow the theme: ${config.theme}
        |No other response
        """.trimMargin()
    )
}