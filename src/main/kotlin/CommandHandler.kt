package me.avo

import me.avo.anki.Anki
import me.avo.jugen.DialogGenerator
import me.avo.jugen.Jugen
import me.avo.jugen.JugenConfig
import me.avo.jugen.SentenceGenerator
import me.avo.jugen.audio.AudioGenerator
import me.avo.jugen.audio.InputReader
import me.avo.model.AzureAiModel
import me.avo.model.LargeLanguageModel
import java.nio.charset.Charset
import java.nio.charset.MalformedInputException

class CommandHandler(private val config: JugenConfig) {
    private val model: LargeLanguageModel = AzureAiModel(config.modelConfig)

    suspend fun anki() {
        val words = Anki(config.ankiConfig)
            .findDifficultCards()
            .mapNotNull { it.fields["Simplified"]?.value }
            .take(5)
        val dialog = DialogGenerator(config, model).generate(words)
        println(dialog)
    }

    fun textToSpeech() {
        val input = InputReader().read()
        AudioGenerator(config.language, config.audioConfig).generate(input)
    }

    suspend fun generate() {
        val jugen = Jugen(
            SentenceGenerator(model, config),
            AudioGenerator(config.language, config.audioConfig)
        )
        jugen.generate()
    }

    suspend fun cycle(chat: Chat): Boolean {
        print("User: ")
        val input = readLineFromUser()
        if (input.isNullOrBlank()) return false

        println()
        if (input == "new()") {
            chat.reset()
        } else if (input.startsWith("model.")) {
            chat.handleModelCommand(input)
        } else {
            chat.addUserMessage(input)
            val response = model.execute(chat)
            chat.addResponse(response)
            println("LLM: $response\n")
        }

        return true
    }

    private fun readLineFromUser(): String? {
        try {
            val stringBuilder = StringBuilder()
            var input: String? = ""
            while (input != null) {
                input = readLine()
                stringBuilder.appendLine(input)
            }

            return stringBuilder.toString().trim()
        } catch (e: MalformedInputException) {
            val charset = Charset.defaultCharset()
            println(charset)
            println("Error reading input: ${e.message}")
            return null;
        }
    }
}