package me.avo.jugen.image

import me.avo.model.image.ImageModel
import me.avo.model.image.ImageOptions

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