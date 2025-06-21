package me.avo.model.image

interface ImageModel {
    suspend fun generateImage(
        prompt: String,
        options: ImageOptions = ImageOptions()): ImageResponse
}