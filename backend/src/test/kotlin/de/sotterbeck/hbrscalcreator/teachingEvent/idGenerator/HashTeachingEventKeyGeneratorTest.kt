package de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator


import assertk.assertThat
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Test

class HashTeachingEventKeyGeneratorTest {

    private val uut = HashTeachingEventKeyGenerator("SHA-256")

    @Test
    fun `should generate unique keys`() {
        val teachingEvent1 = mapOf(
            "semester" to "BI 1",
            "event" to "Einführung in die Analysis (V)",
            "day" to "MO",
            "startTime" to "08:00",
            "endTime" to "10:00",
            "room" to "A123",
            "instructor" to "Instructor 1",
        )
        val teachingEvent2 = mapOf(
            "semester" to "BI 2",
            "event" to "Netze",
            "day" to "MO",
            "startTime" to "08:00",
            "endTime" to "10:00",
            "room" to "A123",
            "instructor" to "Instructor 2",
        )

        val key1 = uut.generateKey(teachingEvent1)
        val key2 = uut.generateKey(teachingEvent2)

        assertThat(key1).isNotEqualTo(key2)
    }
}