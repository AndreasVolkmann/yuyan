package me.avo.jugen

import me.avo.Chat
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
            ModelOptions(maxTokens = 1000)
        )

        val result = largeLanguageModel.execute(chat)
        println(result)
        return result
    }

    private fun getMessage(words: List<String>): ChatMessage {
        return ChatMessage(
            "user",
            """
            |Generate a dialog in ${config.language.name} for learning purposes with the words ${
                words.joinToString(
                    ", "
                )
            }.
            |No other response
        )
        """.trimIndent()
        )
    }
}