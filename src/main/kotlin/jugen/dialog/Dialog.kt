package me.avo.jugen.dialog

data class Dialog(
    val words: List<String>,
    val lines: List<DialogLine>,
)

data class DialogLine(
    val speaker: String,
    val text: String,
) {
    override fun toString() = "$speaker: $text"
}
