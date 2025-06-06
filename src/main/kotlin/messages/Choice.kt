package me.avo.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Choice(
    val index: Int,
    val message: ChatMessage,
    
    @SerialName("finish_reason")
    val finishReason: String) {
}