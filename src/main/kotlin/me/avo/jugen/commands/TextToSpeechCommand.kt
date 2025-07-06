package me.avo.jugen.commands

import me.avo.Command
import me.avo.jugen.JugenConfig
import me.avo.jugen.audio.AudioGenerator
import me.avo.jugen.audio.InputReader
import me.avo.jugen.audio.SsmlBuilder

class TextToSpeechCommand(
    private val config: JugenConfig
): Command { 

    override val id: String = "tts"

    override suspend fun execute(arguments: List<String>) {
        val input = InputReader().read()
        val ssml = SsmlBuilder(config.language, config.audioConfig).createSsml(input)
        AudioGenerator(config.audioConfig).generate(ssml.content)
    }
}