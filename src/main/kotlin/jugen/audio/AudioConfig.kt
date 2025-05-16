package me.avo.jugen.audio

data class AudioConfig(
    val subscriptionKey: String,
    val region: String,
    val voiceName: String = Voice.Xiaochen.name,
    val style: String = "documentary-narration",
    val styleDegree: Double = 1.0,
    val language: String = "zh-CN",
) {
    val voice: Voice = Voice.valueOf(voiceName)
}
