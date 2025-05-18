package me.avo.anki

import kotlinx.serialization.Serializable

@Serializable
data class AnkiResponse<T>(
    val result: T?,
    val error: String?) {
}