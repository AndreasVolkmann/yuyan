package me.avo

import me.avo.anki.Anki
import me.avo.jugen.Jugen
import me.avo.jugen.JugenConfig
import me.avo.jugen.audio.AudioGenerator
import me.avo.jugen.audio.InputReader
import me.avo.jugen.audio.SsmlBuilder
import me.avo.model.AzureAiModel
import me.avo.model.LargeLanguageModel
import java.nio.charset.Charset
import java.nio.charset.MalformedInputException

class CommandHandler(private val config: JugenConfig) {
    private val model: LargeLanguageModel = AzureAiModel(config.modelConfig)
    private val jugen = Jugen(config, model)

    suspend fun anki() {
        val words = Anki(config.ankiConfig)
            .findDifficultCards()
            .mapNotNull { it.fields["Simplified"]?.value }
//            .take(10)
        
        jugen.generateDialog(words)
    }

    fun textToSpeech() {
        val input = InputReader().read()
        val ssml = SsmlBuilder(config.language, config.audioConfig).createSsml(input)
        AudioGenerator(config.audioConfig).generate(ssml)
    }

    suspend fun generate() {
        jugen.generateSentence()
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