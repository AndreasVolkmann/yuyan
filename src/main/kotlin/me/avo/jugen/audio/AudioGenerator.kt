package me.avo.jugen.audio

import com.microsoft.cognitiveservices.speech.*

class AudioGenerator(
    val config: AudioConfig
) {
    fun generate(ssml: String): SpeechSynthesisResult {
        val synthesizer = getSynthesizer(config)
        val result = synthesizer.SpeakSsml(ssml)
        handleResult(result, ssml)
        return result
    }

    private fun handleResult(result: SpeechSynthesisResult, ssml: String) = when (result.reason) {
        ResultReason.SynthesizingAudioCompleted -> {}
        ResultReason.Canceled -> handleCancellation(result, ssml)
        else -> throw IllegalStateException("Unexpected value: ${result.reason}")
    }

    private fun handleCancellation(result: SpeechSynthesisResult, ssml: String) {
        val cancellation = SpeechSynthesisCancellationDetails.fromResult(result)
        throw IllegalStateException("Synthesis canceled: ${cancellation.errorCode}: ${cancellation.errorDetails}: $ssml")
    }

    private fun getSynthesizer(config: AudioConfig): SpeechSynthesizer {
        val speechConfig = SpeechConfig.fromSubscription(config.subscriptionKey, config.region)
        speechConfig.speechSynthesisVoiceName = Voice.random().id
        return SpeechSynthesizer(speechConfig)
    }
}