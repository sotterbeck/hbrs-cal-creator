package de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator


import assertk.assertThat
import assertk.assertions.isNotEqualTo
import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventDto
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class HashTeachingEventKeyGeneratorTest {

    private val uut = HashTeachingEventKeyGenerator("SHA-256")

    @Test
    fun `should generate unique keys`() {
        val teachingEvent1 = TeachingEventDto(
            eventTitle = "Einführung in die Analysis (V)",
            semester = "BI 1",
            instructor = "Instructor 2",
            day = DayOfWeek.MONDAY,
            startTime = LocalTime.of(8, 0),
            endTime = LocalTime.of(10, 0),
            dateOfFirstOccurrence = LocalDate.of(2024, 4, 8),
            dateOfLastOccurrence = LocalDate.of(2024, 7, 1),
            room = "B456",
            period = "08.04.2024-08.07.2024  (KW 15-28)"
        )
        val teachingEvent2 = TeachingEventDto(
            eventTitle = "Einführung in die Analysis (V)",
            semester = "BI 1",
            instructor = "Instructor 2",
            day = DayOfWeek.TUESDAY,
            startTime = LocalTime.of(8, 0),
            endTime = LocalTime.of(10, 0),
            dateOfFirstOccurrence = LocalDate.of(2024, 4, 8),
            dateOfLastOccurrence = LocalDate.of(2024, 7, 1),
            room = "B456",
            period = "08.04.2024-08.07.2024  (KW 15-28)"
        )

        val key1 = uut.generateKey(teachingEvent1)
        val key2 = uut.generateKey(teachingEvent2)

        assertThat(key1).isNotEqualTo(key2)
    }
}