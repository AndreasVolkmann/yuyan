package me.avo

import com.apurebase.arkenv.Arkenv
import com.apurebase.arkenv.feature.EnvironmentVariableFeature
import com.apurebase.arkenv.util.parse
import me.avo.jugen.JugenConfig

suspend fun main(args: Array<String>) {
    println(args.firstOrNull())
    val config = Arkenv.parse<JugenConfig>(args) { +EnvironmentVariableFeature(dotEnvFilePath = ".env") }
    val handler = CommandHandler(config)

//    textToSpeech(config)
//    return


    handler.anki()

    return

    if (config.generate) {
        handler.generate()
        return
    } else {
        val chat = Chat()

        while (true) {
            val status = handler.cycle(chat)
            if (!status) break
        }
    }
}

