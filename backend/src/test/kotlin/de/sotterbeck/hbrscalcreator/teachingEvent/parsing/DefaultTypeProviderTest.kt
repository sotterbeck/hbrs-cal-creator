package de.sotterbeck.hbrscalcreator.teachingEvent.parsing

import assertk.assertThat
import assertk.assertions.containsOnly
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.impl.DefaultTypeProvider
import org.junit.jupiter.api.Test

class DefaultTypeProviderTest {

    private val uut = DefaultTypeProvider

    @Test
    fun `should find type when its a lecture`() {
        val eventTitle = "Programmierung in C (V)"

        val result = uut.typesForEvent(eventTitle)

        assertThat(result).containsOnly(EventType.LECTURE)
    }

    @Test
    fun `should find type when its an exercise`() {
        val eventTitle = "Programmierung in C Gr.3 (Ü)"

        val result = uut.typesForEvent(eventTitle)

        assertThat(result).containsOnly(EventType.EXERCISE)
    }

    @Test
    fun `should find type when its a practical`() {
        val eventTitle = "Netze Gr.1 (P)"

        val result = uut.typesForEvent(eventTitle)

        assertThat(result).containsOnly(EventType.PRACTICAL)
    }

    @Test
    fun `should find type when its a tutorial`() {
        val eventTitle = "DMLA-Tutorium (T)"

        val result = uut.typesForEvent(eventTitle)

        assertThat(result).containsOnly(EventType.TUTORIAL)
    }

    @Test
    fun `should find type when its a seminar`() {
        val eventTitle = "Literatur-Seminar (S)"

        val result = uut.typesForEvent(eventTitle)

        assertThat(result).containsOnly(EventType.SEMINAR)
    }

    @Test
    fun `should find type when its a project`() {
        val eventTitle = "Projektarbeit (Pj)"

        val result = uut.typesForEvent(eventTitle)

        assertThat(result).containsOnly(EventType.PROJECT)
    }

}