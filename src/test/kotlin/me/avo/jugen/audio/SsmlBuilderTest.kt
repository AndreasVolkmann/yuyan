package me.avo.jugen.audio

import me.avo.jugen.Language
import me.avo.jugen.dialog.Dialog
import me.avo.jugen.dialog.DialogLine
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class SsmlBuilderTest {

    @Test
    fun `createSsml should use a voice from available voices`() {
        val config = AudioConfig("test-key", "test-region")
        val ssmlBuilder = SsmlBuilder(Language.Chinese, config)
        
        val ssml = ssmlBuilder.createSsml("Hello World")
        
        // Verify that the SSML contains one of the available voice IDs
        val availableVoiceIds = Voice.values().map { it.id }
        assertTrue(
            availableVoiceIds.any { voiceId -> ssml.contains(voiceId) },
            "SSML should contain a voice from available voices"
        )
    }

    @Test
    fun `createDialogSsml should use voices from available voices`() {
        val config = AudioConfig("test-key", "test-region")
        val ssmlBuilder = SsmlBuilder(Language.Chinese, config)
        
        val dialog = Dialog(
            words = listOf("hello", "world"),
            lines = listOf(
                DialogLine("Speaker1", "Hello"),
                DialogLine("Speaker2", "World")
            )
        )
        
        val ssml = ssmlBuilder.createDialogSsml(dialog)
        
        // Verify that the SSML contains at least one of the available voice IDs
        val availableVoiceIds = Voice.values().map { it.id }
        assertTrue(
            availableVoiceIds.any { voiceId -> ssml.contains(voiceId) },
            "Dialog SSML should contain voices from available voices"
        )
    }
}