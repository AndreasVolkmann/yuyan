package me.avo

interface Command {
    
    val id: String
    
    suspend fun execute(arguments: List<String>)
}