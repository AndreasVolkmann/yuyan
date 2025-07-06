package me.avo.typing

data class DictionaryEntry(
    val timeGauge: Int,
    val phraseOffset: Int,
    val keycodeOffset: Int,
    val phrase: String,
    val keycodes: List<String>,
    val keycodeBytes: List<Byte>
)