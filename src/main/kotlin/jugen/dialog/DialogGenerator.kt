package me.avo.jugen.dialog

import me.avo.Chat
import me.avo.jugen.JugenConfig
import me.avo.messages.ChatMessage
import me.avo.model.LargeLanguageModel
import me.avo.model.ModelOptions

class DialogGenerator(
    private val config: JugenConfig,
    private val largeLanguageModel: LargeLanguageModel
) {
    suspend fun generate(words: List<String>): String {
        val chat = Chat(
            mutableListOf(getMessage(words)),
            ModelOptions(maxTokens = 1000, temperature = 0.7)
        )

        return largeLanguageModel.execute(chat)
    }

    private fun getMessage(words: List<String>) = ChatMessage(
        "user",
        """
        |Generate a dialog in ${config.language.name} for learning purposes with the words ${
            words.joinToString(", ")
        }.
        |The dialog should be between two people, and the words should be used in a natural way.
        |Make it interesting and engaging, with a natural flow of conversation.
        |Preface each line with the speaker's name, followed by a colon.
        """.trimIndent()
    )
}