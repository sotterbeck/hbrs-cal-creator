package de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class CombinedTeachingEventKeyGeneratorTest {

    private val uut = CombinedTeachingEventKeyGenerator()

    @Test
    fun `should generate valid key`() {
        val teachingEvent = mapOf(
            "semester" to "BI 1",
            "event" to "Einführung in die Analysis (V)",
            "instructor" to "Instructor 2",
            "day" to "MO",
            "startTime" to "08:00",
            "endTime" to "10:00",
        )

        val key = uut.generateKey(teachingEvent)

        assertThat(key).isEqualTo("bi1-einfuehrungindieanalysisv-instructor2-mo-0800-1000")
    }
}