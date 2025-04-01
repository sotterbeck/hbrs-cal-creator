export function getTimeIntervals() {
    const timeIntervals: string[] = [];
    for (let hour = 8; hour <= 20; hour++) {
        for (let minute of ["00", "15", "30", "45"]) {
            timeIntervals.push(`${hour.toString().padStart(2, '0')}:${minute}`);
        }
    }
    return timeIntervals;
}

export function getEventPosition(event: EventModel) {
    const start = [+event.startTime.split(":")[0], +event.startTime.split(":")[1]];
    const end = [+event.endTime.split(":")[0], +event.endTime.split(":")[1]];
    //First start is 8 | 1 hour is divided in 4 rows | first row for events is 2
    const rowStart = (((start[0] - 8) * 4) + (start[1] / 15) + 2)
    //Difference between start and end
    const rowSpan = ((end[0] - 8) * 4 + (end[1] / 15)+2) - rowStart
    const weekdayToColMap: { [index: string]: any } = {
        Mo: 2,
        Di: 3,
        Mi: 4,
        Do: 5,
        Fr: 6
    }
    const colStart = weekdayToColMap[event.day];
    return {rowStart, rowSpan, colStart}
}