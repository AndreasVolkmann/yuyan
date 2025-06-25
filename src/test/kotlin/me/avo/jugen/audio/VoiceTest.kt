package me.avo.jugen.audio

import me.avo.jugen.Language
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals

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
        val targetLanguage = Language.Chinese
        // Test multiple times to ensure all returned voices are Chinese
        repeat(10) {
            val randomVoice = Voice.random(targetLanguage)
            assertEquals(targetLanguage, randomVoice.language, "Random voice should match specified language")
        }
    }
    
    @Test
    fun `voice language should match Language enum by id`() {
        // Test that voice language mapping uses Language enum entries, not hardcoded values
        val chineseVoice = Voice.Xiaochen
        val japaneseVoice = Voice.Nanami
        
        assertEquals(Language.Chinese, chineseVoice.language)
        assertEquals(Language.Japanese, japaneseVoice.language)
        
        // Verify the language id matches the voice id prefix
        assertEquals(chineseVoice.id.take(5), chineseVoice.language.id)
        assertEquals(japaneseVoice.id.take(5), japaneseVoice.language.id)
    }
    
    @Test
    fun `all voices should have valid language mapping`() {
        // Test that every voice can be mapped to a valid language
        Voice.entries.forEach { voice ->
            val languagePrefix = voice.id.take(5)
            val expectedLanguage = Language.entries.find { it.id == languagePrefix }
            
            assertTrue(expectedLanguage != null, "Voice ${voice.name} with prefix $languagePrefix should have a matching Language")
            assertEquals(expectedLanguage, voice.language, "Voice ${voice.name} should map to correct language")
        }
    }
}