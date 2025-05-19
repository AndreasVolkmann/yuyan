package me.avo.jugen.dialog

import me.avo.Chat
import me.avo.jugen.JugenConfig
import me.avo.messages.ChatMessage
import me.avo.model.LargeLanguageModel
import me.avo.model.ModelOptions

class ComprehensionGenerator(
    private val config: JugenConfig,
    private val largeLanguageModel: LargeLanguageModel
) {

    suspend fun generateComprehension(words: List<String>, input: String): String {
        val chat = Chat(
            mutableListOf(
                ChatMessage("user", getInstructions(words, input))),
            ModelOptions(
                maxTokens = 1000,
                temperature = 0.7,
            )
        )

        return largeLanguageModel.execute(chat)
    }

    private fun getInstructions(words: List<String>, input: String): String = """
        |Generate a comprehension exercise in ${config.language.name} based on the following dialog:
        |$input
        |
        |Focus on the understanding of the words: ${words.joinToString(", ")}.
        |Create questions that test the comprehension of the dialog.
        |Make it engaging and suitable for language learners.
        """.trimIndent()
}