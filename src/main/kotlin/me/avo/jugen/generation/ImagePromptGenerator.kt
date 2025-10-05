package me.avo.jugen.generation

import me.avo.Chat
import me.avo.jugen.JugenConfig
import me.avo.llm.messages.ChatMessage
import me.avo.llm.model.LargeLanguageModel
import me.avo.llm.model.ModelOptions

class ImagePromptGenerator(val languageModel: LargeLanguageModel, val config: JugenConfig) {
    
    suspend fun generate(input: String): String {
        val chat = Chat(
            mutableListOf(
                ChatMessage(
                    "system",
                    "Create a detailed image prompt in English for the scenario provided by user.\n" +
                            "Use character names where appropriate and provide context on the theme."
                ),
                ChatMessage("user", input)),
            ModelOptions(maxTokens = 1000, temperature = 0.7)
        )
        
        return languageModel.execute(chat)
    }
}