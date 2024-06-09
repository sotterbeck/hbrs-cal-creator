package de.sotterbeck.hbrscalcreator.teachingEvent.entity

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class OddWeekTeachingEventTest {

    @Test
    fun `should throw exception when first occurrence is in even week`() {
        val dateInEvenWeek = LocalDate.of(2024, 4, 29)

        assertThrows<IllegalArgumentException> {
            OddWeekTeachingEvent(
                title = "Programming 1 (V)",
                room = "A-123",
                instructor = "Instructor",
                start = LocalDateTime.of(dateInEvenWeek, LocalTime.of(8, 0)),
                end = LocalDateTime.of(dateInEvenWeek, LocalTime.of(9, 30)),
                dateOfLastOccurrence = LocalDate.of(2024, 7, 8)
            )
        }
    }

    @Test
    fun `should not throw exception when first occurrence is in odd week`() {
        val dateInOddWeek = LocalDate.of(2024, 4, 8)

        assertDoesNotThrow {
            OddWeekTeachingEvent(
                title = "Programming 1 (V)",
                room = "A-123",
                instructor = "Instructor",
                start = LocalDateTime.of(dateInOddWeek, LocalTime.of(8, 0)),
                end = LocalDateTime.of(dateInOddWeek, LocalTime.of(9, 30)),
                dateOfLastOccurrence = LocalDate.of(2024, 7, 8)
            )
        }
    }

    @Test
    fun `should have a interval of 2`() {
        val date = LocalDate.of(2024, 4, 8)

        val teachingEvent = OddWeekTeachingEvent(
            title = "Programming 1 (V)",
            room = "A-123",
            instructor = "Instructor",
            start = LocalDateTime.of(date, LocalTime.of(8, 0)),
            end = LocalDateTime.of(date, LocalTime.of(9, 30)),
            dateOfLastOccurrence = LocalDate.of(2024, 7, 8)
        )

        assertThat(teachingEvent.interval).isEqualTo(2)
    }
}