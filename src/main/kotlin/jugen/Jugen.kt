package me.avo.jugen

import me.avo.jugen.audio.AudioFileWriter
import me.avo.jugen.audio.AudioGenerator
import me.avo.jugen.audio.InputReader
import me.avo.jugen.audio.SsmlBuilder
import me.avo.jugen.dialog.DialogGenerator
import me.avo.jugen.dialog.DialogParser
import me.avo.model.LargeLanguageModel

class Jugen(
    config: JugenConfig,
    model: LargeLanguageModel,
) {
    private val sentenceGenerator = SentenceGenerator(model, config)
    private val audioGenerator = AudioGenerator(config.audioConfig)
    private val dialogGenerator = DialogGenerator(config, model)
    private val ssmlBuilder = SsmlBuilder(config.language, config.audioConfig)

    suspend fun generateSentence(): String {
        val word = InputReader().read()
        return generateSentence(word)
    }

    suspend fun generateSentence(input: String): String {
        println("Generating for: $input")
        val sentence = sentenceGenerator.generate(input)
        println(sentence)
        val ssml = ssmlBuilder.createSsml(sentence)
        val audioResult = audioGenerator.generate(ssml)
        AudioFileWriter().saveAudio(input, audioResult)
        return sentence
    }

    suspend fun generateDialog(words: List<String>) {
        println("Generating dialog for words: $words")
        val result = dialogGenerator.generate(words)
        println(result)
        println()
        val dialog = DialogParser().parse(words, result)

        
        dialog.lines.forEach(::println)


        val ssml = ssmlBuilder.createDialogSsml(dialog)
        val audioResult = audioGenerator.generate(ssml)
        AudioFileWriter().saveAudio("dialog", audioResult)
    }
}