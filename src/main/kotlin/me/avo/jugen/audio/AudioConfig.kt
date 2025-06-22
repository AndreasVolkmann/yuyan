package me.avo.jugen.audio

data class AudioConfig(
    val subscriptionKey: String,
    val region: String,
    val style: String = "documentary-narration",
    val styleDegree: Double = 1.0,
)
