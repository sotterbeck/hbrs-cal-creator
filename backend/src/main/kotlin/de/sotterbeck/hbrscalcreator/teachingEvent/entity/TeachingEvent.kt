package de.sotterbeck.hbrscalcreator.teachingEvent.entity

import java.time.LocalDate
import java.time.LocalDateTime

interface TeachingEvent {

    /**
     * The title of the event
     */
    val title: String

    /**
     * The room where the event takes place
     */
    val room: String

    /**
     * The instructor of the event
     */
    val instructor: String

    /**
     * The start date and time of the first occurrence of the event
     */
    val start: LocalDateTime

    /**
     * The end date and time of the first occurrence of the event
     */
    val end: LocalDateTime

    /**
     * The date of the last occurrence of the event
     */
    val dateOfLastOccurrence: LocalDate

    val interval: Int
        get() = 1
}