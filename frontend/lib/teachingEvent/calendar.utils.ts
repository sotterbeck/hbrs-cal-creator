import {rowPositionLookup} from "@/components/teachingEvents/calendar/calendar";
import {cardStyleLookup} from "@/components/teachingEvents/calendar/event-card";

export function getTimeIntervals(startHour: number, endHour: number) {
    const timeIntervals: string[] = [];
    for (let hour = startHour; hour <= endHour; hour++) {
        for (let minute of ['00', '15', '30', '45']) {
            timeIntervals.push(`${hour.toString().padStart(2, '0')}:${minute}`);
        }
    }
    return timeIntervals;
}

export function getCalendarDataDefinition(events: EventModel[]) {
    const calendarDataDefinition: CalendarDataDefinition = {Mo: {events: [], columns: 0}};
    events.forEach(event => {
        const overlap = overlappingElements(calendarDataDefinition.Mo.events, event)
        if(overlap > calendarDataDefinition.Mo.columns) calendarDataDefinition.Mo.columns = overlap;
        const uiEvent = {...event, position: getEventPosition(event) + " " + rowPositionLookup['colStart'][overlap]};
        calendarDataDefinition.Mo.events.push(uiEvent);
    })
    return calendarDataDefinition;
}

function overlappingElements(eventsPerWeekday: EventModel[], eventToCmp: EventModel): number {
    const eventToCmpStart = +eventToCmp.startTime.split(':').join('');
    const eventToCmpEnd = +eventToCmp.endTime.split(':').join('');
    let timesOverlapped = 1;
    eventsPerWeekday.forEach(event => {
        const eventStart = +event.startTime.split(':').join('')
        const eventEnd = +event.endTime.split(':').join('')
        if ((eventStart <= eventToCmpStart && eventToCmpStart <= eventEnd) || (eventStart <= eventToCmpEnd && eventToCmpEnd <= eventEnd)
            || (eventToCmpStart <= eventStart && eventStart <= eventToCmpEnd) || (eventToCmpStart <= eventEnd && eventEnd <= eventToCmpEnd)) timesOverlapped += 1;
    })
    return timesOverlapped;
}

function getEventPosition(event: EventModel) {
    const start = [
        +event.startTime.split(':')[0],
        +event.startTime.split(':')[1],
    ];
    const end = [+event.endTime.split(':')[0], +event.endTime.split(':')[1]];
    //First start is 8 | 1 hour is divided in 4 rows | first row for events is 2
    const rowStart = (start[0] - 8) * 4 + start[1] / 15;
    //Difference between start and end
    const rowSpan = (end[0] - 8) * 4 + end[1] / 15 - rowStart;
    return (
        rowPositionLookup['rowStart'][rowStart] +
        ' ' +
        rowPositionLookup['rowSpan'][rowSpan]
    );
}

export function getEventCardStyle(types: EventType[]) {
    const tokens = types.map((t) => t.token);
    if (tokens.length > 1) return cardStyleLookup['both'];
    if (tokens.includes('V')) return cardStyleLookup['v'];
    if (tokens.includes('Ü')) return cardStyleLookup['ue'];
}

interface CalendarDataDefinition {
    Mo: {
        events: UiEventModel[],
        columns: number
    };
}

interface UiEventModel extends EventModel {
    position: string;
}
