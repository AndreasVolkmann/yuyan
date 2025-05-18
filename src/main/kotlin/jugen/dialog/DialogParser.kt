package me.avo.jugen.dialog

class DialogParser {
    fun parse(words: List<String>, rawDialog: String): Dialog {

        val lines = rawDialog
            .split('\n')
            .filterNot(String::isBlank)
            .map(::toLine)
            .filter { it.speaker.isNotBlank() && it.text.isNotBlank() }
        
        return Dialog(words, lines)
    }

    private fun toLine(input: String): DialogLine {
        val split = input
            .split(':', '：')
            .map { it.trimStart('*').trim() }
        if (split.size > 1) {
            val speaker = split[0].trim()
            val text = split.drop(1).joinToString(":").trim()
            return DialogLine(speaker, text)
        }
        return DialogLine("", input)
    }
}