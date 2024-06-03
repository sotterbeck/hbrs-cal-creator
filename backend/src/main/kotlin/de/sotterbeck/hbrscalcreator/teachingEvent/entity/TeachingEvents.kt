package de.sotterbeck.hbrscalcreator.teachingEvent.entity

import java.time.LocalDate
import java.time.LocalDateTime

class TeachingEvents {

    companion object {

        fun create(
            title: String,
            room: String,
            start: LocalDateTime,
            end: LocalDateTime,
            period: String,
            dateOfLastOccurrence: LocalDate,
        ): TeachingEvent {
            return when {
                period.lowercase().contains("ukw") ->
                    OddWeekTeachingEvent(title, room, start, end, dateOfLastOccurrence)

                period.lowercase().contains("gkw") ->
                    EvenWeekTeachingEvent(title, room, start, end, dateOfLastOccurrence)

                else -> CommonTeachingEvent(title, room, start, end, dateOfLastOccurrence)
            }
        }

    }

}