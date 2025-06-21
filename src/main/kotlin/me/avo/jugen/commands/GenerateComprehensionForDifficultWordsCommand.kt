package me.avo.jugen.commands

import me.avo.anki.service.AnkiService
import me.avo.jugen.Jugen

class GenerateComprehensionForDifficultWordsCommand(
    private val ankiService: AnkiService,
    private val jugen: Jugen
)
    : JugenCommand {

    override val id: String = "generate_comprehension_for_difficult_words"

    override suspend fun execute() {
        val words = ankiService
            .findDifficultCards()
            .mapNotNull { it.fields["Simplified"]?.value }
            .take(10)

        val dialog = jugen.generateDialog(words)

        jugen.generateComprehension(dialog)
    }
}