package me.avo.llm.model

data class ModelOptions(
    var temperature: Double = 1.0,
    var topP: Double = 1.0,
    var maxTokens: Int = 900
)