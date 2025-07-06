package me.avo.typing

import me.avo.typing.dictionary.DictionaryCommand
import me.avo.typing.dictionary.DictionaryDecoder
import me.avo.typing.dictionary.DictionaryEncoder
import me.avo.typing.dictionary.Entry
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DictionaryEncoderTest {
    private val encoder = DictionaryEncoder()
    private val decoder = DictionaryDecoder()

    @Test
    fun encodeAndDecode() {
        val entries = listOf(
            Entry(
                timeGauge = 15,
                phrase = "あい",
                keycodes = listOf("a", "i"),
            ),
            Entry(
                timeGauge = 20,
                phrase = "酒乱",
                keycodes = listOf("shu", "ra", "nn"),
            )
        )

        val encodedBytes = encoder.encode(entries)
        val decodedEntries = decoder.process(DictionaryCommand(encodedBytes)).entries

        assertEquals(entries.size, decodedEntries.size)
        for (i in entries.indices) {
            val e = entries[i]
            val decoded = decodedEntries[i]
            assertEquals(e.timeGauge, decoded.timeGauge)
            assertEquals(e.phrase, decoded.phrase)
            assertEquals(e.keycodes, decoded.keycodes)
        }
    }
}