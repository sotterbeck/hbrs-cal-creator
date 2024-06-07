package de.sotterbeck.hbrscalcreator.teachingEvent

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventResponse.EventModel
import de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator.CombinedTeachingEventKeyGenerator
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.impl.DefaultTeachingEventParsingFactory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.web.server.ResponseStatusException
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class TeachingEventPresenterImplTest {

    private val parsingFactory = DefaultTeachingEventParsingFactory()
    private val uut = TeachingEventPresenterImpl(
        DefaultTeachingEventParsingFactory(),
        CombinedTeachingEventKeyGenerator(parsingFactory)
    )

    @Test
    fun `should format lecture`() {
        val lecture = TeachingEventDto(
            day = DayOfWeek.TUESDAY,
            startTime = LocalTime.of(9, 45),
            endTime = LocalTime.of(11, 15),
            dateOfFirstOccurrence = LocalDate.of(2024, 4, 8),
            dateOfLastOccurrence = LocalDate.of(2024, 7, 1),
            room = "B456",
            eventTitle = "Einführung in die Analysis (V)",
            period = "08.04.2024-08.07.2024  (KW 15-28)",
            instructor = "Instructor 2",
            semester = "BI 1"
        )

        val formattedLecture = uut.presentSuccess(listOf(lecture)).first()

        assertThat(formattedLecture).all {
            prop(EventModel::id).isEqualTo("BI1-EinfuehrungInDieAnalysis-Instructor2-Di-09:45-11:15-V")
            prop(EventModel::semester).isEqualTo("BI 1")
            prop(EventModel::title).isEqualTo("Einführung in die Analysis (V)")
            prop(EventModel::module).isEqualTo("Einführung in die Analysis")
            prop(EventModel::day).isEqualTo("Di")
            prop(EventModel::startTime).isEqualTo("09:45")
            prop(EventModel::endTime).isEqualTo("11:15")
            prop(EventModel::room).isEqualTo("B456")
            prop(EventModel::instructor).isEqualTo("Instructor 2")
            prop(EventModel::types).isEqualTo(
                setOf(
                    TeachingEventResponse.EventType(
                        "Vorlesung",
                        "V"
                    )
                )
            )
            prop(EventModel::group).isNull()
        }
    }

    @Test
    fun `should format exercise`() {
        val exercise = TeachingEventDto(
            day = DayOfWeek.MONDAY,
            startTime = LocalTime.of(8, 0),
            endTime = LocalTime.of(9, 30),
            dateOfFirstOccurrence = LocalDate.of(2024, 4, 8),
            dateOfLastOccurrence = LocalDate.of(2024, 7, 1),
            room = "A123",
            eventTitle = "Event 1 Gr.3 (Ü)",
            period = "08.04.2024-01.07.2024  (uKW (ab KW 15))",
            instructor = "Instructor 1",
            semester = "BI 1"
        )

        val formattedExercise = uut.presentSuccess(listOf(exercise)).first()

        assertThat(formattedExercise).all {
            prop(EventModel::id).isEqualTo("BI1-Event1-Instructor1-Mo-08:00-09:30-U")
            prop(EventModel::semester).isEqualTo("BI 1")
            prop(EventModel::title).isEqualTo("Event 1 Gr.3 (Ü)")
            prop(EventModel::module).isEqualTo("Event 1")
            prop(EventModel::day).isEqualTo("Mo")
            prop(EventModel::startTime).isEqualTo("08:00")
            prop(EventModel::endTime).isEqualTo("09:30")
            prop(EventModel::room).isEqualTo("A123")
            prop(EventModel::instructor).isEqualTo("Instructor 1")
            prop(EventModel::types).isEqualTo(
                setOf(
                    TeachingEventResponse.EventType(
                        "Übung",
                        "Ü"
                    )
                )
            )
            prop(EventModel::group).isEqualTo("3")
        }
    }

    @Test
    fun `should present error as internal server error`() {
        val message = "Some error message"

        assertThrows<ResponseStatusException> { uut.presentFailure(message) }
    }
}