package de.sotterbeck.hbrscalcreator.teachingEvent

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.impl.DefaultTeachingEventMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.DayOfWeek

class DefaultTeachingEventMapperTest {

    private val uut = DefaultTeachingEventMapper

    @Test
    fun `should throw exception when keys are missing`() {
        val teachingEvent = mapOf(
            "room" to "A123",
            "event" to "Event 1 Gr.3 (Ü)",
            "period" to "08.04.2024-01.07.2024  (uKW (ab KW 15))",
            "instructor" to "Instructor 1"
        )

        assertThrows<IllegalStateException> {
            uut.toDto(teachingEvent, "bi1")
        }
    }

    @Test
    fun `should parse time attributes correctly`() {
        val teachingEvent = mapOf(
            "day" to "Mo",
            "startTime" to "8:00",
            "endTime" to "9:30",
            "room" to "A123",
            "event" to "Event 1 Gr.3 (Ü)",
            "period" to "08.04.2024-01.07.2024  (uKW (ab KW 15))",
            "instructor" to "Instructor 1"
        )

        val result = uut.toDto(teachingEvent, "bi1")

        assertAll {
            assertThat(result.startTime.hour).isEqualTo(8)
            assertThat(result.startTime.minute).isEqualTo(0)
            assertThat(result.endTime.hour).isEqualTo(9)
            assertThat(result.endTime.minute).isEqualTo(30)
            assertThat(result.day).isEqualTo(DayOfWeek.MONDAY)
        }
    }

    @Test
    fun `should parse start and end date`() {
        val teachingEvent = mapOf(
            "day" to "Mo",
            "startTime" to "8:00",
            "endTime" to "9:30",
            "room" to "A123",
            "event" to "Event 1 Gr.3 (Ü)",
            "period" to "08.04.2024-01.07.2024  (uKW (ab KW 15))",
            "instructor" to "Instructor 1"
        )

            val result = uut.toDto(teachingEvent, "bi1")

            assertAll {
                assertThat(result.dateOfFirstOccurrence.year).isEqualTo(2024)
                assertThat(result.dateOfFirstOccurrence.monthValue).isEqualTo(4)
                assertThat(result.dateOfFirstOccurrence.dayOfMonth).isEqualTo(8)
                assertThat(result.dateOfLastOccurrence.year).isEqualTo(2024)
                assertThat(result.dateOfLastOccurrence.monthValue).isEqualTo(7)
                assertThat(result.dateOfLastOccurrence.dayOfMonth).isEqualTo(1)
            }
    }
}