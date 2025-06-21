package me.avo.anki.util

/**
 * Utility class for handling Unicode escape sequences.
 */
object UnicodeEscapeUtils {

    /**
     * Unescapes Unicode escape sequences in a string.
     * 
     * @param escaped The string containing Unicode escape sequences (\uXXXX format)
     * @return The string with all Unicode escape sequences replaced with their respective characters
     */
    fun unescapeUnicode(escaped: String): String {
        val regex = Regex("\\\\u([0-9a-fA-F]{4})")
        return regex.replace(escaped) { matchResult ->
            val hexValue = matchResult.groupValues[1]
            hexValue.toInt(16).toChar().toString()
        }
    }
}
