package me.avo.anki.util

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

class UnicodeEscapeUtilsTest {

    data class TestCase(
        val description: String,
        val input: String,
        val expected: String
    )

    @TestFactory
    fun `test unescapeUnicode with various inputs`() = listOf(
        TestCase(
            "single unicode character",
            "\\u00A9",
            "©"
        ),
        TestCase(
            "multiple unicode characters",
            "\\u00A9 \\u00AE \\u2122",
            "© ® ™"
        ),
        TestCase(
            "mixed text and unicode",
            "Copyright \\u00A9 2025, Trademark \\u2122",
            "Copyright © 2025, Trademark ™"
        ),
        TestCase(
            "no unicode characters",
            "Just a regular string",
            "Just a regular string"
        ),
        TestCase(
            "empty string",
            "",
            ""
        ),
        TestCase(
            "Japanese characters",
            "\\u3053\\u3093\\u306B\\u3061\\u306F",
            "こんにちは"
        ),
        TestCase(
            "Chinese characters",
            "jugen_\\u4F60\\u597D\\u4E16\\u754C",
            "jugen_你好世界"
        )
    ).map { testCase ->
        DynamicTest.dynamicTest("Test unescapeUnicode with ${testCase.description}") {
            val result = UnicodeEscapeUtils.unescapeUnicode(testCase.input)
            assertEquals(testCase.expected, result)
        }
    }
}
