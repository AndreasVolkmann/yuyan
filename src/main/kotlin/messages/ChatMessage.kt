package me.avo.messages

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(val role: String, val content: String)