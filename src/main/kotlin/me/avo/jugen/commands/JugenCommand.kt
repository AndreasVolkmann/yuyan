package me.avo.jugen.commands

interface JugenCommand {
    
    val id: String
    
    suspend fun execute()
}