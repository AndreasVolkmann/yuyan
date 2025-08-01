package me.avo

import me.avo.anki.service.AnkiRequestFactory
import me.avo.anki.service.AnkiService
import me.avo.anki.service.AnkiViaHttpClient
import me.avo.jugen.Jugen
import me.avo.jugen.JugenConfig
import me.avo.jugen.commands.*
import me.avo.llm.model.AzureAiModel
import me.avo.llm.model.LargeLanguageModel
import me.avo.typing.TypingCommand

class CommandHandler(config: JugenConfig) {
    private val model: LargeLanguageModel = AzureAiModel(config.modelConfig)
    private val jugen = Jugen(config, model)
    private val ankiService = AnkiService(
        config.ankiConfig, AnkiViaHttpClient(config.ankiConfig), AnkiRequestFactory()
    )
    private val commands = listOf(
        GenerateSentenceCommand(jugen),
        BackfillCardsWithoutSampleSentenceCommand(ankiService, jugen, config.ankiConfig),
        GenerateComprehensionForDifficultWordsCommand(ankiService, jugen),
        ChatCommand(model),
        TextToSpeechCommand(config),
        GenerateImageCommand(config.imageModelConfig),
        TypingCommand(ankiService),
    )

    suspend fun execute(command: String?, arguments: List<String>) =
        commands.firstOrNull { it.id == (command ?: "chat") }
            ?.execute(arguments)
            ?: throw IllegalArgumentException(
                "Unknown command: $command. Available commands: ${commands.map { it.id }.joinToString(", ")}"
            )
}