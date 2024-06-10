package de.sotterbeck.hbrscalcreator.teachingEvent.entity

import assertk.assertThat
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class TeachingEventsFactoryTest {

    @Test
    fun `should return common teaching event when period does not contain uKW or gKW`() {
        val periodWithoutEvenOrOddWeek = "08.04.2024-08.07.2024  (KW 15-28)"

        val teachingEvent = TeachingEvents.create(
            title = "Programming 1 (V)",
            room = "A-123",
            instructor = "Instructor 1",
            start = LocalDateTime.of(2024, 4, 8, 8, 0),
            end = LocalDateTime.of(2024, 4, 8, 9, 30),
            period = periodWithoutEvenOrOddWeek,
            dateOfLastOccurrence = LocalDate.of(2024, 7, 8)
        )

        assertThat(teachingEvent).isInstanceOf<CommonTeachingEvent>()
    }

    @Test
    fun `should return odd week teaching event when period contains uKW`() {
        val periodWithOddWeek = "08.04.2024-08.07.2024  (uKW (ab KW 15))"

        val teachingEvent = TeachingEvents.create(
            title = "Programming 1 (V)",
            room = "A-123",
            instructor = "Instructor 1",
            start = LocalDateTime.of(2024, 4, 8, 8, 0),
            end = LocalDateTime.of(2024, 4, 8, 9, 30),
            period = periodWithOddWeek,
            dateOfLastOccurrence = LocalDate.of(2024, 7, 8)
        )

        assertThat(teachingEvent).isInstanceOf<OddWeekTeachingEvent>()
    }

    @Test
    fun `should return even week teaching event when period contains gKW`() {
        val periodWithEvenWeek = "29.04.2024-08.07.2024  (gKW (ab KW18))"

        val teachingEvent = TeachingEvents.create(
            title = "Programming 1 (V)",
            instructor = "Instructor 1",
            start = LocalDateTime.of(2024, 4, 29, 8, 0),
            end = LocalDateTime.of(2024, 4, 29, 9, 30),
            room = "A-123",
            period = periodWithEvenWeek,
            dateOfLastOccurrence = LocalDate.of(2024, 7, 8)
        )

        assertThat(teachingEvent).isInstanceOf<EvenWeekTeachingEvent>()
    }
}