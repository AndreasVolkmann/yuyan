package me.avo.jugen.commands

import me.avo.Command
import me.avo.jugen.JugenConfig
import me.avo.jugen.generation.ImagePromptGenerator
import me.avo.jugen.image.ImageGenerator
import me.avo.llm.model.LargeLanguageModel
import me.avo.llm.model.ModelConfig
import me.avo.llm.model.image.AzureAiImageModel

class GenerateImageCommand(
    private val imageModelConfig: ModelConfig,
    private val largeLanguageModel: LargeLanguageModel,
    private val config: JugenConfig
) : Command {
    override val id = "generate-image"

    override suspend fun execute(arguments: List<String>) {
        val input = "由于轻信了胖虎的恶作剧，大雄又一次上当，以为抽屉里会变出未来的道具。"
        val imagePrompt = ImagePromptGenerator(largeLanguageModel, config)
            .generate(input)
        println(imagePrompt)
        ImageGenerator(AzureAiImageModel(imageModelConfig))
            .generateImage(imagePrompt)
    }
}