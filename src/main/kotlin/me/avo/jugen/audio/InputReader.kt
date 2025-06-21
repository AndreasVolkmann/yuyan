package me.avo.jugen.audio

import java.io.File

class InputReader {
    fun read(): String {
        val file = File("input.txt")
        if (file.exists()) {
            return file.readText().trim()
        }
        throw IllegalStateException("input.txt not found")
    }

}