package me.avo.llm.model

data class ModelConfig(
    val host: String,
    val apiKey: String,
    val id: String? = null,
) {
    init {
        if (host.startsWith("http:") || host.startsWith("https:")) {
            throw IllegalArgumentException("Host should not start with http:// or https://")
        }
        if (host.endsWith(".services.ai.azure.com") && id == null) {
            throw IllegalArgumentException("Service endpoint requires a model id to be set")
        }
    }
    
    val isServiceEndpoint: Boolean = id != null
}