import { getTimeIntervals } from '@/lib/teachingEvent/calendar.utils';

interface CalendarProps {
  selectedEvents: EventModel[];
}

export default function Calendar(props: CalendarProps) {
  const timeIntervals = getTimeIntervals();
  const weekDays = ['Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag'];
  return (
    <>
      <div className={'container'}>
        <div
          className={
            'grid grid-cols-[auto,1fr,1fr,1fr,1fr,1fr] grid-rows-[auto_repeat(52,minmax(40px,1fr))] text-left text-xl text-muted-foreground'
          }
        >
          <div className="w-16"></div>
          {weekDays.map((weekDay, index) => (
            <div key={index}>{weekDay}</div>
          ))}
          {timeIntervals.map((time, index) => (
            <div key={index} className={'col-start-1 min-h-4 tabular-nums text-sm text-muted-foreground'}>
              {time.endsWith('00') && <div>{time}</div>}
            </div>
          ))}
          <div className="col-start-2 row-span-2 row-start-3 rounded bg-blue-500 p-2 text-white">
            Meeting
          </div>
        </div>
      </div>
    </>
  );
}
