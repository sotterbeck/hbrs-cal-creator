package de.sotterbeck.hbrscalcreator.ical

import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventDto

interface ICalGenerator {

    /**
     * Generates an iCal file from the given teaching events
     */
    fun generateICal(teachingEvents: List<TeachingEventDto>): String

}