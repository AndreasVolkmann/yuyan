package me.avo.jugen.commands

import me.avo.jugen.Jugen

class GenerateSentenceCommand(
    private val jugen: Jugen
) : JugenCommand {
    override val id = "generate-sentence"

    override suspend fun execute() {
        jugen.generateSentence()
    }
}