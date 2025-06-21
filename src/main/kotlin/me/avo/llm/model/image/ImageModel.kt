package me.avo.llm.model.image

interface ImageModel {
    suspend fun generateImage(
        prompt: String,
        options: ImageOptions = ImageOptions()
    ): ImageResponse
}