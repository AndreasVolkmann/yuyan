package me.avo

import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import me.avo.anki.AnkiConfig
import me.avo.jugen.JugenConfig
import me.avo.jugen.Language
import me.avo.jugen.audio.AudioConfig
import me.avo.llm.model.ModelConfig
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CommandHandlerTest {

    private fun createMockConfig(): JugenConfig {
        val config = mockk<JugenConfig>(relaxed = true)
        
        // Mock the required properties
        io.mockk.every { config.language } returns Language.Chinese
        io.mockk.every { config.modelConfig } returns ModelConfig("test-host", "test-key")
        io.mockk.every { config.imageModelConfig } returns ModelConfig("test-image-host", "test-image-key")
        io.mockk.every { config.audioConfig } returns AudioConfig("test-sub-key", "test-region")
        io.mockk.every { config.ankiConfig } returns AnkiConfig(deckName = "test-deck")
        
        return config
    }

    @Test
    fun `execute should throw exception for unknown command`() = runTest {
        val config = createMockConfig()
        val commandHandler = CommandHandler(config)

        val exception = assertFailsWith<IllegalArgumentException> {
            commandHandler.execute("unknown-command")
        }

        assertTrue(exception.message!!.contains("Unknown command: unknown-command"))
    }

    @Test
    fun `execute should list available commands in error message for unknown command`() = runTest {
        val config = createMockConfig()
        val commandHandler = CommandHandler(config)

        val exception = assertFailsWith<IllegalArgumentException> {
            commandHandler.execute("non-existent-command")
        }

        val message = exception.message!!
        assertTrue(message.contains("Unknown command: non-existent-command"))
        assertTrue(message.contains("Available commands:"))
        
        // Should contain available commands
        assertTrue(message.contains("generate-sentence"))
        assertTrue(message.contains("backfill"))
        assertTrue(message.contains("generate_comprehension_for_difficult_words"))
        assertTrue(message.contains("chat"))
        assertTrue(message.contains("tts"))
        assertTrue(message.contains("generate-image"))
    }

    @Test
    fun `execute should use chat as default command when null provided`() = runTest {
        val config = createMockConfig()
        val commandHandler = CommandHandler(config)

        // This should not throw an exception as "chat" command exists
        // We can't test the actual execution without setting up the full environment,
        // but we can verify it doesn't throw an unknown command exception
        try {
            commandHandler.execute(null)
        } catch (e: IllegalArgumentException) {
            if (e.message?.contains("Unknown command") == true) {
                throw AssertionError("Should not throw unknown command exception for null input (should default to 'chat')")
            }
            // Other exceptions are fine as they're from trying to actually execute the command
        } catch (e: Exception) {
            // Other exceptions are expected when trying to actually run without proper setup
        }
    }
}