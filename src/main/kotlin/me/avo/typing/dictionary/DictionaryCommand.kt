package me.avo.typing.dictionary

import java.io.File

data class DictionaryCommand(
    val bytes: ByteArray
) {
    constructor(filePath: String) : this(File(filePath).readBytes())
}