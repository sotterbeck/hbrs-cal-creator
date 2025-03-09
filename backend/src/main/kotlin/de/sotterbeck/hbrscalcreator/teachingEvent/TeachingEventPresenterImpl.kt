package de.sotterbeck.hbrscalcreator.teachingEvent

import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventResponse.EventModel
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.EventType
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.TeachingEventParsingFactory
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.time.format.TextStyle
import java.util.*

class TeachingEventPresenterImpl(
    parsingFactory: TeachingEventParsingFactory,
) : TeachingEventPresenter {

    private val moduleParser = parsingFactory.createModuleParser()
    private val groupParser = parsingFactory.createEventTypeParser()
    private val typeProvider = parsingFactory.createTypeProvider()

    override fun presentSuccess(teachingEvents: List<TeachingEventDto>): List<EventModel> {
        return teachingEvents.map { format(it) }
    }

    override fun presentFailure(message: String): Nothing {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, message)
    }

    private fun format(event: TeachingEventDto): EventModel {
        val weekDay = event.day.getDisplayName(TextStyle.SHORT, Locale.GERMAN).replace(".", "")
        val types = typeProvider.typesForEvent(event.eventTitle)
            .map { it.toModel() }
            .toSet()

        val group = groupParser.parse(event.eventTitle).ifBlank { null }
        val module = moduleParser.parse(event.eventTitle)

        return EventModel(
            id = event.id,
            semester = event.semester,
            title = event.eventTitle,
            module = module,
            day = weekDay,
            startTime = event.startTime.toString(),
            endTime = event.endTime.toString(),
            room = event.room,
            instructor = event.instructor,
            types = types,
            group = group
        )
    }

    private fun EventType.toModel(): TeachingEventResponse.EventType {
        return when (this) {
            EventType.LECTURE -> TeachingEventResponse.EventType("Vorlesung", "V")
            EventType.EXERCISE -> TeachingEventResponse.EventType("Übung", "Ü")
            EventType.PRACTICAL -> TeachingEventResponse.EventType("Praktikum", "P")
            EventType.TUTORIAL -> TeachingEventResponse.EventType("Tutorium", "T")
            EventType.SEMINAR -> TeachingEventResponse.EventType("Seminar", "S")
            EventType.PROJECT -> TeachingEventResponse.EventType("Projekt", "Pj")
        }
    }

}