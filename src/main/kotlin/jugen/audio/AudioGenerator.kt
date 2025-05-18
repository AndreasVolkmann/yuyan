package me.avo.jugen.audio

import com.microsoft.cognitiveservices.speech.*
import me.avo.jugen.Language

class AudioGenerator(
    val language: Language,
    val config: AudioConfig
) {
    fun generate(input: String): SpeechSynthesisResult {
        val ssml = createSsml(input)
        val synthesizer = getSynthesizer(config)
        val result = synthesizer.SpeakSsml(ssml)
        handleResult(result)
        return result
    }

    //language=XML
    private fun createSsml(input: String): String = """
        <speak version="1.0" xmlns="http://www.w3.org/2001/10/synthesis" xmlns:mstts="https://www.w3.org/2001/mstts" xml:lang="${language.id}">
            <voice name="${config.voice.id}">
                <mstts:express-as style="${config.style}" styledegree="${config.styleDegree}">
                    $input
                </mstts:express-as>
            </voice>
        </speak>
        """.trimIndent()

    private fun handleResult(result: SpeechSynthesisResult) = when (result.reason) {
        ResultReason.SynthesizingAudioCompleted -> {}
        ResultReason.Canceled -> handleCancellation(result)
        else -> throw IllegalStateException("Unexpected value: ${result.reason}")
    }

    private fun handleCancellation(result: SpeechSynthesisResult) {
        val cancellation = SpeechSynthesisCancellationDetails.fromResult(result)
        throw IllegalStateException("Synthesis canceled: ${cancellation.errorCode}: ${cancellation.errorDetails}")
    }

    private fun getSynthesizer(config: AudioConfig): SpeechSynthesizer {
        val speechConfig = SpeechConfig.fromSubscription(config.subscriptionKey, config.region)
        speechConfig.speechSynthesisVoiceName = config.voice.id
        return SpeechSynthesizer(speechConfig)
    }
}