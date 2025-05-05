package me.avo.jugen

import java.io.File

class Jugen(
    val sentenceGenerator: SentenceGenerator,
    val audioGenerator: AudioGenerator) {
    
    suspend fun generate() {
        val word = File("input.txt").readText().trim()
        println("Generating for word: $word")
        val sentence = sentenceGenerator.generate(word)
        println(sentence)
        val audioResult = audioGenerator.generate(sentence)
        AudioFileWriter().saveAudio(word, audioResult)
    }
}