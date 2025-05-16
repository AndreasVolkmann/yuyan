package me.avo.jugen

import me.avo.jugen.audio.AudioFileWriter
import me.avo.jugen.audio.AudioGenerator
import me.avo.jugen.audio.InputReader

class Jugen(
    val sentenceGenerator: SentenceGenerator,
    val audioGenerator: AudioGenerator
) {
    
    suspend fun generate() {
        val word = InputReader().read()
        println("Generating for word: $word")
        val sentence = sentenceGenerator.generate(word)
        println(sentence)
        val audioResult = audioGenerator.generate(sentence)
        AudioFileWriter().saveAudio(word, audioResult)
    }

}