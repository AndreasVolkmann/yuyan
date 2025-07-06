package me.avo.jugen.commands

import me.avo.Chat
import me.avo.Command
import me.avo.llm.model.LargeLanguageModel
import java.nio.charset.Charset
import java.nio.charset.MalformedInputException

class ChatCommand(
    private val model: LargeLanguageModel
): Command {
    override val id = "chat"

    override suspend fun execute(arguments: List<String>) {
        val chat = Chat()

        while (true) {
            val status = cycle(chat)
            if (!status) break
        }
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