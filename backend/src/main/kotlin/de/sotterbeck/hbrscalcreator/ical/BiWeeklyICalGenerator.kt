package de.sotterbeck.hbrscalcreator.ical

import biweekly.ICalendar
import biweekly.component.VEvent
import biweekly.io.TimezoneAssignment
import biweekly.util.Frequency
import biweekly.util.Recurrence
import de.sotterbeck.hbrscalcreator.common.toUtilDate
import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventDto
import de.sotterbeck.hbrscalcreator.teachingEvent.entity.TeachingEvents
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone

/**
 * Generates an iCal file using the [biweekly](https://github.com/mangstadt/biweekly) iCal library
 */
class BiWeeklyICalGenerator : ICalGenerator {

    override fun generateICal(teachingEvents: List<TeachingEventDto>): String {
        val iCal = ICalendar()

        // Set the timezone for all events from system default (handles DST automatically)
        val zoneId = ZoneId.systemDefault()
        val timezone = TimeZone.getTimeZone(zoneId)
        val timezoneAssignment = TimezoneAssignment(timezone, zoneId.id)
        iCal.timezoneInfo.defaultTimezone = timezoneAssignment

        for (teachingEvent in teachingEvents) {
            val vEvent = createVEvent(teachingEvent, iCal, timezoneAssignment)
            iCal.addEvent(vEvent)
        }

        return iCal.write()
    }

    private fun createVEvent(
        teachingEvent: TeachingEventDto,
        iCal: ICalendar,
        timezoneAssignment: TimezoneAssignment
    ): VEvent {
        val vEvent = VEvent()

        val eventEntity = TeachingEvents.create(
            title = teachingEvent.eventTitle,
            room = teachingEvent.room,
            instructor = teachingEvent.instructor,
            start = LocalDateTime.of(teachingEvent.dateOfFirstOccurrence, teachingEvent.startTime),
            end = LocalDateTime.of(teachingEvent.dateOfFirstOccurrence, teachingEvent.endTime),
            period = teachingEvent.period,
            dateOfLastOccurrence = teachingEvent.dateOfLastOccurrence
        )

        val recurrenceRule = Recurrence.Builder(Frequency.WEEKLY)
            .interval(eventEntity.interval)
            .until(eventEntity.dateOfLastOccurrence.plusDays(1).toUtilDate())
            .build()

        vEvent.setSummary(eventEntity.title)
        vEvent.setDescription(eventEntity.instructor)
        vEvent.setLocation(eventEntity.room)

        // Set dates with time component (true = has time, not all-day)
        vEvent.setDateStart(eventEntity.start.toUtilDate(), true)
        vEvent.setDateEnd(eventEntity.end.toUtilDate(), true)

        // Explicitly set timezone for the date properties
        iCal.timezoneInfo.setTimezone(vEvent.dateStart, timezoneAssignment)
        iCal.timezoneInfo.setTimezone(vEvent.dateEnd, timezoneAssignment)

        vEvent.setRecurrenceRule(recurrenceRule)

        return vEvent
    }
}