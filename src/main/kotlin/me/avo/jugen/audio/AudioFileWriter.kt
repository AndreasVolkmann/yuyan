package me.avo.jugen.audio

import com.microsoft.cognitiveservices.speech.SpeechSynthesisResult
import java.io.File

class AudioFileWriter {
    fun saveAudio(name: String, result: SpeechSynthesisResult): File {
        println(result.audioLength)

        val audioFolder = File("audio")
        if (!audioFolder.exists()) {
            audioFolder.mkdirs()
        }

        val audioData = result.audioData
        val fileName = "jugen_${name}.wav"
        val file = File(audioFolder, fileName)
        file.writeBytes(audioData)
        return file
    }

}