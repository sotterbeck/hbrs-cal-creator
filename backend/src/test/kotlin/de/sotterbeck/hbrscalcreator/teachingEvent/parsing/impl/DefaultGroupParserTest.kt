package de.sotterbeck.hbrscalcreator.teachingEvent.parsing.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DefaultGroupParserTest {

    private val uut = DefaultGroupParser()

    @Test
    fun `should return empty string when title has no group`() {
        val title = "Programming 1"
        val result = uut.parse(title)

        assertEquals("", result)
    }

    @Test
    fun `should return group when title has group`() {
        val title = "Programming 1 Gr.D (Ü)"
        val result = uut.parse(title)

        assertEquals("D", result)
    }

    @Test
    fun `should return group when there is whitespace between`() {
        val title = "Programming 1 Gr. D (Ü)"

        val result = uut.parse(title)

        assertEquals("D", result)
    }
}