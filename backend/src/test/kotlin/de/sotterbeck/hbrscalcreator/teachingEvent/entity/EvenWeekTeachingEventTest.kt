package de.sotterbeck.hbrscalcreator.teachingEvent.entity

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class EvenWeekTeachingEventTest {

    @Test
    fun `should throw exception when first occurrence is in odd week`() {
        val dateInOddWeek = LocalDate.of(2024, 4, 8)

        assertThrows<IllegalArgumentException> {
            EvenWeekTeachingEvent(
                title = "Programming 1 (V)",
                room = "A-123",
                start = LocalDateTime.of(dateInOddWeek, LocalTime.of(8, 0)),
                end = LocalDateTime.of(dateInOddWeek, LocalTime.of(9, 30)),
                dateOfLastOccurrence = LocalDate.of(2024, 7, 8)
            )
        }
    }

    @Test
    fun `should not throw exception when fist occurrence is in event week`() {
        val dateInEvenWeek = LocalDate.of(2024, 4, 29)

        assertDoesNotThrow {
            EvenWeekTeachingEvent(
                title = "Programming 1 (V)",
                room = "A-123",
                start = LocalDateTime.of(dateInEvenWeek, LocalTime.of(8, 0)),
                end = LocalDateTime.of(dateInEvenWeek, LocalTime.of(9, 30)),
                dateOfLastOccurrence = LocalDate.of(2024, 7, 8)
            )
        }
    }

    @Test
    fun `should have a intervall of 2`() {
        val date = LocalDate.of(2024, 4, 29)

        val teachingEvent = EvenWeekTeachingEvent(
            title = "Programming 1 (V)",
            room = "A-123",
            start = LocalDateTime.of(date, LocalTime.of(8, 0)),
            end = LocalDateTime.of(date, LocalTime.of(9, 30)),
            dateOfLastOccurrence = LocalDate.of(2024, 7, 8)
        )

        assertThat(teachingEvent.interval).isEqualTo(2)
    }
}