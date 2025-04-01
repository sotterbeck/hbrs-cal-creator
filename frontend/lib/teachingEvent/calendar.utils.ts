export function getTimeIntervals() {
    const timeIntervals: string[] = [];
    for (let hour = 8; hour <= 20; hour++) {
        for (let minute of ["00", "15", "30", "45"]) {
            timeIntervals.push(`${hour.toString().padStart(2, '0')}:${minute}`);
        }
    }
    return timeIntervals;
}