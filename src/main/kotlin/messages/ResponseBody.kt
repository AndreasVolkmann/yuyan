package me.avo.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseBody(
    val id: String,
    @SerialName("object")
    val obj: String,
    val model: String,
    val choices: List<Choice>)
