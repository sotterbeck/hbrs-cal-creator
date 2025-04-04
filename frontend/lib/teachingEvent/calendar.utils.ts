export function getTimeIntervals(startHour: number, endHour: number): string[] {
  const timeIntervals: string[] = [];
  for (let hour = startHour; hour <= endHour; hour++) {
    for (let minute of ['00', '15', '30', '45']) {
      timeIntervals.push(`${hour.toString().padStart(2, '0')}:${minute}`);
    }
  }
  return timeIntervals;
}

/**
 * Gets data per weekday
 * **/
export function getCalendarData(events: EventModel[]): WeekdayModel[] {
  const calendarData: WeekdayModel[] = Object.entries(WeekdaysEnum).map(
    ([day]) => ({ name: day, events: [], columns: 1 }) as WeekdayModel,
  );
  events.forEach((event) => {
    //find array position (Weekday) for event
    const index = calendarData.findIndex((day) => day.name === event.day);
    if (index == -1)
      throw new DOMException('CalendarUtils: Invalid Event date in calendar');
    const rowPosition = getEventRowPosition(event);
    calendarData[index].events.push({
      ...event,
      position: {
        rowStart: rowPosition[0],
        rowSpan: rowPosition[1],
      },
    });
  });
  return calendarData;
}

/**
 * Dynamically selects event card styling
 * **/
export function getEventCardStyle(types: EventType[]): string {
  const tokens = types.map((t) => t.token);
  if (tokens.length > 1) return 'both';
  if (tokens.includes('V')) return 'v';
  if (tokens.includes('Ü')) return 'ue';
  if (tokens.includes('P')) return 'p';
  return '';
}

/**
 * Is eventToCmp overlapping any other events, move it to next free column
 * **/

/*function getEventColumnPosition(
  eventsPerWeekday: UiEventModel[],
  eventToCmp: EventModel,
): number {
  const eventToCmpStart = +eventToCmp.startTime.split(':').join('');
  const eventToCmpEnd = +eventToCmp.endTime.split(':').join('');
  let column = 1;
  eventsPerWeekday.forEach((event) => {
    const eventStart = +event.startTime.split(':').join('');
    const eventEnd = +event.endTime.split(':').join('');
    if (
      (eventStart <= eventToCmpStart && eventToCmpStart <= eventEnd) ||
      (eventStart <= eventToCmpEnd && eventToCmpEnd <= eventEnd) ||
      (eventToCmpStart <= eventStart && eventStart <= eventToCmpEnd) ||
      (eventToCmpStart <= eventEnd && eventEnd <= eventToCmpEnd)
    )
      column += 1;
  });
  return column;
}*/

/**
 * Calculates rowStart and rowSpan,
 * every row has to be 15min
 * **/
function getEventRowPosition(event: EventModel) {
  const start = [
    +event.startTime.split(':')[0],
    +event.startTime.split(':')[1],
  ];
  const end = [+event.endTime.split(':')[0], +event.endTime.split(':')[1]];
  const rowStart = (start[0] - 8) * 4 + start[1] / 15 + 1;
  const rowSpan = (end[0] - 8) * 4 + end[1] / 15 - rowStart + 1;
  return [rowStart, rowSpan];
}

interface WeekdayModel {
  name: string;
  events: UiEventModel[];
  columns: number;
}

interface UiEventModel extends EventModel {
  position: {
    rowStart: number;
    rowSpan: number;
  };
}

enum WeekdaysEnum {
  Mo = 'Mo',
  Di = 'Di',
  Mi = 'Mi',
  Do = 'Do',
  Fr = 'Fr',
}
