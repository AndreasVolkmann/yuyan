package me.avo.llm.model

data class ModelConfig(
    val host: String,
    val apiKey: String,
) {
    init {
        if (host.startsWith("http:") || host.startsWith("https:")) {
            throw IllegalArgumentException("Host should not start with http:// or https://")
        }
    }
}