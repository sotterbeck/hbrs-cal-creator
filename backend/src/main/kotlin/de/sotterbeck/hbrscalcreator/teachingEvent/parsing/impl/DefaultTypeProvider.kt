package de.sotterbeck.hbrscalcreator.teachingEvent.parsing.impl

import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.EventType
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.TypeProvider
import org.slf4j.LoggerFactory

object DefaultTypeProvider : TypeProvider {

    private val logger = LoggerFactory.getLogger(DefaultTypeProvider::class.java)

    private val typeMap = mapOf(
        "(V)" to setOf(EventType.LECTURE),
        "(Ü)" to setOf(EventType.EXERCISE),
        "(P)" to setOf(EventType.PRACTICAL),
        "(T)" to setOf(EventType.TUTORIAL),
        "(S)" to setOf(EventType.SEMINAR),
        "(Pj)" to setOf(EventType.PROJECT),
        "(Vorlesung und Übung)" to setOf(EventType.LECTURE, EventType.EXERCISE),
        "(Ü/P)" to setOf(EventType.EXERCISE, EventType.PRACTICAL),
        "(V/Ü)" to setOf(EventType.LECTURE, EventType.EXERCISE),
        "(VÜ)" to setOf(EventType.LECTURE, EventType.EXERCISE),
        "(SU)" to setOf(EventType.SEMINAR, EventType.EXERCISE),
        "(VÜPS)" to setOf(EventType.LECTURE, EventType.EXERCISE, EventType.PRACTICAL, EventType.SEMINAR),
        "(Tutorium) (Vorlesung und Übung)" to setOf(EventType.TUTORIAL, EventType.LECTURE, EventType.EXERCISE),
    )

    override val typeKeys get() = typeMap.keys

    override fun typesForEvent(eventTitle: String): Set<EventType> {
        for (key in typeKeys) {
            if (eventTitle.contains(key)) {
                return typeMap[key]!!
            }
        }

        logger.warn("No type found for event title: $eventTitle")

        return emptySet()
    }
}