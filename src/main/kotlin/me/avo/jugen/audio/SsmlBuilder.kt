package me.avo.jugen.audio

import me.avo.jugen.Language
import me.avo.jugen.dialog.Dialog

class SsmlBuilder(
    val language: Language,
    val config: AudioConfig
) {
    fun createSsml(input: String): Ssml {
        val voice = Voice.random()
        val ssmlContent = createSsmlFromLines(
            createSsmlForLine(input, voice)
        )
        return Ssml(ssmlContent, voice)
    }

    fun createDialogSsml(dialog: Dialog): String {
        val voiceTags = dialog.lines.joinToString("\n") { l ->
            createSsmlForLine(l.text, Voice.random())
        }

        return createSsmlFromLines(voiceTags)
    }

    //language=XML
    private fun createSsmlFromLines(lines: String): String = """
        <speak version="1.0" xmlns="http://www.w3.org/2001/10/synthesis" xmlns:mstts="https://www.w3.org/2001/mstts" xml:lang="${language.id}">
            $lines
        </speak>
        """.trimIndent()

    //language=XML
    private fun createSsmlForLine(line: String, voice: Voice) = """
        <voice name="${voice.id}">
            <mstts:express-as style="${config.style}" styledegree="${config.styleDegree}">
                $line
            </mstts:express-as>
        </voice>
    """.trimIndent()
}