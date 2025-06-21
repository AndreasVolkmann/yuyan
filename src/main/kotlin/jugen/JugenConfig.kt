package me.avo.jugen

import com.apurebase.arkenv.module.module
import com.apurebase.arkenv.util.argument
import me.avo.anki.AnkiConfig
import me.avo.jugen.audio.AudioConfig
import me.avo.model.ModelConfig

class JugenConfig(
    val generate: Boolean = true
) {

    val language: Language by argument("-l", "--language") {
        mapping = { Language.valueOf(it) }
        description = "Language to generate sentences in"
    }

    val modelConfig: ModelConfig by module<ModelConfig> { prefix = "model" }
    val imageModelConfig: ModelConfig by module<ModelConfig> { prefix = "imageModel" }
    val audioConfig: AudioConfig by module<AudioConfig> { prefix = "audio" }
    val ankiConfig: AnkiConfig by module<AnkiConfig> { prefix = "anki" }
}