package me.avo

import com.apurebase.arkenv.Arkenv
import com.apurebase.arkenv.feature.EnvironmentVariableFeature
import com.apurebase.arkenv.util.parse
import me.avo.jugen.JugenConfig

suspend fun main(args: Array<String>) {
    println("Arguments: ${args.joinToString(", ")}")
    val command = args.firstOrNull() ?: throw IllegalArgumentException("No command provided. Please specify a command to execute.")
    val config = Arkenv.parse<JugenConfig>(args) { +EnvironmentVariableFeature(dotEnvFilePath = ".env") }
    val handler = CommandHandler(config)
    handler.execute(command)
}

