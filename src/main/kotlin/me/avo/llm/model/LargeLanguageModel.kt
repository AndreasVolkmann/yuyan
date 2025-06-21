package me.avo.llm.model

import me.avo.Chat

interface LargeLanguageModel {
    
    suspend fun execute(chat: Chat): String
}