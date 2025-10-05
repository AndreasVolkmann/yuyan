package me.avo.jugen.image

import me.avo.llm.model.image.ImageModel
import me.avo.llm.model.image.ImageOptions

class ImageGenerator(
    private val imageModel: ImageModel,
) {
    suspend fun generateImage(
        prompt: String,
    ) {
        val imageResponse = imageModel.generateImage(
            prompt, ImageOptions(style = "", quality = "undefined"))
        // Failed to generate image: 400 - Bad Request, {"error":{"code":"Bad Request","message":"Content rejected due to violence detection in prompt.","status":400}}
        println(imageResponse)
    }
}