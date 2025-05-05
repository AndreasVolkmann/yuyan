package me.avo

import me.avo.messages.ChatMessage
import me.avo.model.ModelOptions

class Chat(
    val messages: MutableList<ChatMessage> = mutableListOf(),
    val modelOptions: ModelOptions = ModelOptions()
) {
    fun reset() {
        messages.clear()
    }

    fun addUserMessage(message: String) {
        messages.add(ChatMessage("user", message))
    }

    fun addResponse(response: String) {
        messages.add(ChatMessage("assistant", response))
    }
    
    fun handleModelCommand(input: String) {
        val split = input.split(".", limit = 3)
        val command = split[1]
        val value = if (split.size > 2) split[2] else ""
        when (command) {
            "info" -> {}
            "temp", "temperature" -> modelOptions.temperature = value.toDouble()
            "topP" -> modelOptions.topP = value.toDouble()
            "maxTokens" -> modelOptions.maxTokens = value.toInt()
            "model" -> modelOptions.model = value
            else -> println("Unknown property: $command")
        }
        println(modelOptions)
    }
}