package me.avo.llm.messages

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(val role: String, val content: String)