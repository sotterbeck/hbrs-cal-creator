package de.sotterbeck.hbrscalcreator.ical

import biweekly.ICalendar
import biweekly.component.VEvent
import biweekly.util.Frequency
import biweekly.util.Recurrence
import de.sotterbeck.hbrscalcreator.common.toUtilDate
import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventDto
import de.sotterbeck.hbrscalcreator.teachingEvent.entity.TeachingEvents
import java.time.LocalDateTime

/**
 * Generates an iCal file using the [biweekly](https://github.com/mangstadt/biweekly) iCal library
 */
class BiWeeklyICalGenerator : ICalGenerator {

    override fun generateICal(teachingEvents: List<TeachingEventDto>): String {
        val iCal = ICalendar()

        for (teachingEvent in teachingEvents) {
            val vEvent = createVEvent(teachingEvent)
            iCal.addEvent(vEvent)
        }

        return iCal.write()
    }

    private fun createVEvent(teachingEvent: TeachingEventDto): VEvent {
        val vEvent = VEvent()

        val eventEntity = TeachingEvents.create(
            title = teachingEvent.eventTitle,
            room = teachingEvent.room,
            start = LocalDateTime.of(teachingEvent.startDate, teachingEvent.startTime),
            end = LocalDateTime.of(teachingEvent.startDate, teachingEvent.endTime),
            period = teachingEvent.period,
            dateOfLastOccurrence = teachingEvent.endDate
        )

        val recurrenceRule = Recurrence.Builder(Frequency.WEEKLY)
            .interval(eventEntity.interval)
            .until(eventEntity.dateOfLastOccurrence.toUtilDate())
            .build()

        vEvent.setSummary(eventEntity.title)
        vEvent.setDateStart(eventEntity.start.toUtilDate())
        vEvent.setDateEnd(eventEntity.end.toUtilDate())
        vEvent.setRecurrenceRule(recurrenceRule)

        return vEvent
    }
}