package me.avo.jugen

data class AudioConfig(
    val subscriptionKey: String,
    val region: String,
    val voiceName: String = "zh-CN-YunjianNeural",
    val style: String = "documentary-narration",
    val styleDegree: Double = 1.5,
)
