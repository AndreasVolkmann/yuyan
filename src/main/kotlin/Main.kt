package me.avo

import com.apurebase.arkenv.Arkenv
import com.apurebase.arkenv.feature.EnvironmentVariableFeature
import com.apurebase.arkenv.util.parse
import me.avo.jugen.AudioGenerator
import me.avo.jugen.Jugen
import me.avo.jugen.JugenConfig
import me.avo.jugen.SentenceGenerator
import me.avo.model.AzureAiModel
import me.avo.model.LargeLanguageModel
import java.nio.charset.Charset
import java.nio.charset.MalformedInputException

suspend fun main(args: Array<String>) {
    println(args.firstOrNull())
    val config = Arkenv.parse<JugenConfig>(args) { +EnvironmentVariableFeature(dotEnvFilePath = ".env") }
    
    if (config.generate) {
        generate(config)
        return
    }
    else {
        val model: LargeLanguageModel = AzureAiModel(config.modelConfig)
        val chat = Chat()
    
        while (true) {
            val status = cycle(chat, model)
            if (!status) break
        }
    }
}

private suspend fun generate(config: JugenConfig) {
    val genModel = AzureAiModel(config.modelConfig)
    val sentenceGenerator = Jugen(
        SentenceGenerator(genModel),
        AudioGenerator(config.audioConfig)
    )
    sentenceGenerator.generate()
}

private suspend fun cycle(chat: Chat, model: LargeLanguageModel): Boolean {
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
    }
    catch (e: MalformedInputException) {
        val charset = Charset.defaultCharset()
        println(charset)
        println("Error reading input: ${e.message}")
        return null;
    }
}