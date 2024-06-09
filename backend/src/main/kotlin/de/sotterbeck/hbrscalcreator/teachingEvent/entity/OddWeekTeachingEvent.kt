package de.sotterbeck.hbrscalcreator.teachingEvent.entity

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoField

class OddWeekTeachingEvent(
    override val title: String,
    override val room: String,
    override val instructor: String,
    override val start: LocalDateTime,
    override val end: LocalDateTime,
    override val dateOfLastOccurrence: LocalDate
) : TeachingEvent {

    init {
        val calenderNumber = start[ChronoField.ALIGNED_WEEK_OF_YEAR]
        require(calenderNumber % 2 != 0) { "The start date must be in an odd week" }
    }

    override val interval: Int
        get() = 2
}