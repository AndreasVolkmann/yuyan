package me.avo.jugen.commands

import me.avo.jugen.image.ImageGenerator
import me.avo.llm.model.ModelConfig
import me.avo.llm.model.image.AzureAiImageModel

class GenerateImageCommand(
    private val imageModelConfig: ModelConfig
) : JugenCommand {
    override val id = "generate-image"

    override suspend fun execute() {
        ImageGenerator(AzureAiImageModel(imageModelConfig))
            .generateImage("这里禁止出入。")
    }
}