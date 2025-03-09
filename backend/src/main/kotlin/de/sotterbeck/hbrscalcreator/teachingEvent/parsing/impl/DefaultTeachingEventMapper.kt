package de.sotterbeck.hbrscalcreator.teachingEvent.parsing.impl

import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventDto
import de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator.TeachingEventKeyGenerator
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.TeachingEventMapper
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Default implementation of [TeachingEventMapper]
 *
 * This implementation assumes that the input map contains the following keys: day, startTime, endTime, room, event, period, instructor
 */
class DefaultTeachingEventMapper(private val keyGenerator: TeachingEventKeyGenerator) : TeachingEventMapper {

    private val weekDayFormatter = DateTimeFormatter.ofPattern("E", Locale.GERMAN)
    private val timeFormatter = DateTimeFormatter.ofPattern("H:mm")
    private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    override fun toDto(teachingEvent: Map<String, String>, semesterName: String): TeachingEventDto {
        val weekDayString = teachingEvent["day"]?.plus(".") ?: error("Day is missing")

        val day = weekDayFormatter.parse(weekDayString)

        val period = teachingEvent["period"] ?: error("Period is missing")
        val datePeriod = period.substringBefore("(").trim()

        val startDateString = datePeriod.substringBefore("-").trim()
        val endDateString = datePeriod.substringAfter("-").trim()

        return TeachingEventDto(
            id = keyGenerator.generateKey(teachingEvent),
            semester = semesterName,
            day = DayOfWeek.from(day),
            startTime = LocalTime.parse(teachingEvent["startTime"] ?: error("Start time is missing"), timeFormatter),
            endTime = LocalTime.parse(teachingEvent["endTime"] ?: error("End time is missing"), timeFormatter),
            dateOfFirstOccurrence = LocalDate.parse(startDateString, dateFormatter),
            dateOfLastOccurrence = LocalDate.parse(endDateString, dateFormatter),
            room = teachingEvent["room"] ?: error("Room is missing"),
            eventTitle = teachingEvent["event"] ?: error("Event title is missing"),
            period = period,
            instructor = teachingEvent["instructor"] ?: error("Instructor is missing")
        )
    }

}