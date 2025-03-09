package de.sotterbeck.hbrscalcreator.teachingEvent

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

data class TeachingEventDto(
    val id: String,
    val semester: String,
    val day: DayOfWeek,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val dateOfFirstOccurrence: LocalDate,
    val dateOfLastOccurrence: LocalDate,
    val room: String,
    val eventTitle: String,
    val period: String,
    val instructor: String
)
