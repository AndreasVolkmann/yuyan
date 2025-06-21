package me.avo.llm.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestBody(
    val messages: List<ChatMessage>,

    @SerialName("max_tokens")
    val maxTokens: Int,
    val temperature: Float,

    @SerialName("top_p")
    val topP: Float
)
