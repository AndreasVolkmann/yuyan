package me.avo.jugen.commands

import me.avo.Command
import me.avo.jugen.Jugen

class GenerateSentenceCommand(
    private val jugen: Jugen
) : Command {
    override val id = "generate-sentence"

    override suspend fun execute(arguments: List<String>) {
        jugen.generateSentence()
    }
}