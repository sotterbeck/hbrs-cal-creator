package de.sotterbeck.hbrscalcreator.teachingEvent.parsing

/**
 * Strategy for extracting a simple teaching event property from a string.
 * For example, the course name or the group name.
 */
interface EventPropertyParser {

    fun parse(eventTitle: String): String

}