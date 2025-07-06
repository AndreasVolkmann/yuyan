package me.avo.typing

data class Entry(
    val phrase: String,
    val keycodes: List<String>,
    val timeGauge: Int = 15)