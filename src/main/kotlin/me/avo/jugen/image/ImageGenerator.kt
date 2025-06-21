package me.avo.jugen.image

import me.avo.llm.model.image.ImageModel
import me.avo.llm.model.image.ImageOptions

class ImageGenerator(
    private val imageModel: ImageModel,
) {
    suspend fun generateImage(
        prompt: String,
    ) {
        val imageResponse = imageModel.generateImage(prompt, ImageOptions())
        println(imageResponse)
    }
}