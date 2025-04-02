import {rowPositionLookup} from "@/components/teachingEvents/calendar/calendar";
import {borderStyleLookup} from "@/components/teachingEvents/calendar/event-card";

export function getTimeIntervals(startHour: number, endHour: number) {
  const timeIntervals: string[] = [];
  for (let hour = startHour; hour <= endHour; hour++) {
    for (let minute of ['00', '15', '30', '45']) {
      timeIntervals.push(`${hour.toString().padStart(2, '0')}:${minute}`);
    }
  }
  return timeIntervals;
}

export function getEventCardBorderStyles(types: EventType[]) {
  const tokens = types.map((t) => t.token);
  if (tokens.length > 1) return borderStyleLookup['both'];
  if (tokens.includes('V')) return borderStyleLookup['v'];
  if (tokens.includes('Ü')) return borderStyleLookup['ue'];
}

export function getEventPosition(event: EventModel) {
  const start = [
    +event.startTime.split(':')[0],
    +event.startTime.split(':')[1],
  ];
  const end = [+event.endTime.split(':')[0], +event.endTime.split(':')[1]];
  //First start is 8 | 1 hour is divided in 4 rows | first row for events is 2
  const rowStart = (start[0] - 8) * 4 + start[1] / 15 + 2;
  //Difference between start and end
  const rowSpan = (end[0] - 8) * 4 + end[1] / 15 + 2 - rowStart;
  const weekdayToColMap: { [index: string]: any } = {
    Mo: 2,
    Di: 3,
    Mi: 4,
    Do: 5,
    Fr: 6,
  };
  const colStart = weekdayToColMap[event.day];
  return (
    rowPositionLookup['rowStart'][rowStart] +
    ' ' +
    rowPositionLookup['rowSpan'][rowSpan] +
    ' ' +
    rowPositionLookup['colStart'][colStart]
  );
}
