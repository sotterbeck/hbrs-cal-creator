package de.sotterbeck.hbrscalcreator.teachingEvent.entity

import java.time.LocalDate
import java.time.LocalDateTime

class CommonTeachingEvent(
    override val title: String,
    override val room: String,
    override val start: LocalDateTime,
    override val end: LocalDateTime,
    override val dateOfLastOccurrence: LocalDate,
) : TeachingEvent