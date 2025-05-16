package me.avo.jugen

import java.io.File

class Jugen(
    val sentenceGenerator: SentenceGenerator,
    val audioGenerator: AudioGenerator) {
    
    suspend fun generate() {
        val word = getWord()
        println("Generating for word: $word")
        val sentence = sentenceGenerator.generate(word)
        println(sentence)
        val audioResult = audioGenerator.generate(sentence)
        AudioFileWriter().saveAudio(word, audioResult)
    }

    private fun getWord(): String {
        val file = File("input.txt")
        if (file.exists()) {
            return file.readText().trim()
        }
        throw IllegalStateException("input.txt not found")
    }
}