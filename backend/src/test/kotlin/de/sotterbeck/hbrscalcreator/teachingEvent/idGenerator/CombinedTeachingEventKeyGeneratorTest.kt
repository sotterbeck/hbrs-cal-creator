package de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventDto
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class CombinedTeachingEventKeyGeneratorTest {

    private val uut = CombinedTeachingEventKeyGenerator()

    @Test
    fun `should generate valid key`() {
        val teachingEvent = TeachingEventDto(
            eventTitle = "Einführung in die Analysis (V)",
            semester = "BI 1",
            instructor = "Instructor 2, St.",
            day = DayOfWeek.MONDAY,
            startTime = LocalTime.of(8, 0),
            endTime = LocalTime.of(10, 0),
            dateOfFirstOccurrence = LocalDate.of(2024, 4, 8),
            dateOfLastOccurrence = LocalDate.of(2024, 7, 1),
            room = "B456",
            period = "08.04.2024-08.07.2024  (KW 15-28)"
        )

        val key = uut.generateKey(teachingEvent)

        assertThat(key).isEqualTo("BI1-EinfuehrungInDieAnalysisV-Instructor2St-Mo-0800-1000")
    }
}