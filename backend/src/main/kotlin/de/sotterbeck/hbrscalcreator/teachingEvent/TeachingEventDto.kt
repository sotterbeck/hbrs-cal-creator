package de.sotterbeck.hbrscalcreator.teachingEvent

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

data class TeachingEventDto(
    val semester: String,
    val day: DayOfWeek,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val room: String,
    val eventTitle: String,
    val period: String,
    val instructor: String
)
