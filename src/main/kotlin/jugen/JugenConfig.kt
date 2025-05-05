package me.avo.jugen

import com.apurebase.arkenv.module.module
import me.avo.model.ModelConfig

class JugenConfig(val generate: Boolean = true) {
//    val generate: Boolean by argument("-g", "--generate")
    
    val modelConfig: ModelConfig by module<ModelConfig>()
    val audioConfig: AudioConfig by module<AudioConfig>()
}