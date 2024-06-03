package de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator

import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventDto
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.EventType
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.TeachingEventParsingFactory
import java.time.format.TextStyle
import java.util.*

class CombinedTeachingEventKeyGenerator(parsingFactory: TeachingEventParsingFactory) : TeachingEventKeyGenerator {

    private val moduleParser = parsingFactory.createModuleParser()
    private val typeProvider = parsingFactory.createTypeProvider()

    override fun generateKey(teachingEvent: TeachingEventDto): String {
        val module = moduleParser.parse(teachingEvent.eventTitle)
        val types = typeProvider.typesForEvent(teachingEvent.eventTitle)

        val segments = listOf(
            teachingEvent.semester.replace(" ", ""),
            module.pascalCased(),
            teachingEvent.instructor.pascalCased(),
            teachingEvent.day.getDisplayName(TextStyle.SHORT, Locale.GERMAN).replace(".", ""),
            teachingEvent.startTime.toString(),
            teachingEvent.endTime.toString(),
            toTypeString(types)
        )
        return segments.joinToString(separator = "-").replace("Ü", "U")
    }

    private fun String.pascalCased(): String {
        return this.split(" ").joinToString("") {
            it.replaceFirstChar { char -> char.uppercase() }
        }
    }

    private fun toTypeString(types: Set<EventType>): String {
        return types.joinToString(separator = "") {
            when (it) {
                EventType.LECTURE -> "V"
                EventType.EXERCISE -> "U"
                EventType.PRACTICAL -> "P"
                EventType.TUTORIAL -> "T"
                EventType.SEMINAR -> "S"
                EventType.PROJECT -> "P"
            }
        }
    }

}
