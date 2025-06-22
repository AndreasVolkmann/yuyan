package me.avo.jugen.audio

import me.avo.jugen.Language
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class VoiceTest {

    @Test
    fun `random voice should return one of the available voices`() {
        val availableVoices = Voice.entries.toSet()
        
        // Test multiple times to ensure randomness works
        repeat(10) {
            val randomVoice = Voice.random()
            assertTrue(randomVoice in availableVoices, "Random voice should be one of the available voices")
        }
    }

    @Test
    fun `random voice should eventually return different voices`() {
        val voices = mutableSetOf<Voice>()
        
        // Collect voices from multiple calls
        repeat(50) {
            voices.add(Voice.random())
        }
        
        // We should get at least 2 different voices in 50 attempts
        // (extremely unlikely to get the same voice 50 times in a row)
        assertTrue(voices.size > 1, "Random voice should return different voices over multiple calls")
    }
    
    @Test
    fun `random voice with language should return voice of specified language`() {
        // Test multiple times to ensure all returned voices are Chinese
        repeat(10) {
            val randomVoice = Voice.random(Language.Chinese)
            assertTrue(randomVoice.language == Language.Chinese, "Random voice should match specified language")
        }
    }
}