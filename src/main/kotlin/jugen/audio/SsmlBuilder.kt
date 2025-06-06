package me.avo.jugen.audio

import me.avo.jugen.Language
import me.avo.jugen.dialog.Dialog

class SsmlBuilder(
    val language: Language,
    val config: AudioConfig
) {

    //language=XML
    fun createSsml(input: String): String = """
        <speak version="1.0" xmlns="http://www.w3.org/2001/10/synthesis" xmlns:mstts="https://www.w3.org/2001/mstts" xml:lang="${language.id}">
            ${createSsmlForLine(input, config.voice)}
        </speak>
        """.trimIndent()

    fun createDialogSsml(dialog: Dialog): String {
        val voiceTags = dialog.lines
            .mapIndexed { i, l ->
                createSsmlForLine(
                    l.text,
                    if (i % 2 == 0) config.voice else config.altVoice
                )
            }
            .joinToString("\n")

        //language=XML
        return """
            <speak version="1.0" xmlns="http://www.w3.org/2001/10/synthesis" xmlns:mstts="https://www.w3.org/2001/mstts" xml:lang="${language.id}">
                $voiceTags
            </speak>
            """.trimIndent()
    }

    //language=XML
    private fun createSsmlForLine(line: String, voice: Voice) = """
        <voice name="${voice.id}">
            <mstts:express-as style="${config.style}" styledegree="${config.styleDegree}">
                $line
            </mstts:express-as>
        </voice>
    """.trimIndent()
}