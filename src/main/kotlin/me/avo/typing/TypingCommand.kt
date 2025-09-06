package me.avo.typing

import me.avo.Command
import me.avo.anki.AnkiCard
import me.avo.anki.service.AnkiService
import me.avo.typing.dictionary.DictionaryEncoder
import me.avo.typing.dictionary.Entry
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class TypingCommand(
    private val ankiService: AnkiService) : Command {
    override val id = "totd"

    override suspend fun execute(arguments: List<String>) {
        val fileName = arguments.getOrNull(0) ?: throw IllegalArgumentException("File name must be provided as the first argument.")
        val destinationPath = arguments.getOrNull(1) ?: "output"
        
        // Query for cards under learning with Pinyin.2 and Traditional.
        val cards = ankiService.findCards("prop:due=0 Pinyin.2:_* Traditional:_*")
        if (cards.isEmpty()) {
            throw IllegalStateException("No cards found under learning with Pinyin.2 and Traditional fields.")
        }
        println("Found ${cards.size} cards under learning with Pinyin.2 and Traditional fields.")
     
        val filePath = Paths.get(destinationPath, fileName)  
        generate(filePath, cards.map(::cardToEntry))
    }

    private fun generate(outputPath: Path, newEntries: List<Entry>) {
        val newBytes = DictionaryEncoder().encode(newEntries)
        Files.createDirectories(outputPath.parent)
        Files.write(outputPath, newBytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
        println("Generated file: $outputPath")
    }

    private fun cardToEntry(card: AnkiCard): Entry {
        val pinyin = card.getFieldValue("Pinyin.2")
        val traditional = card.getFieldValue("Traditional")
        return Entry(
            timeGauge = 29, // Default value, can be adjusted
            phrase = traditional,
            keycodes = pinyin.map { it.toString().uppercase() }
        )
    } 
}