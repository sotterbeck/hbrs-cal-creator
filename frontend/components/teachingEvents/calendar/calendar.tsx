import {
  getEventPosition,
  getTimeIntervals,
} from '@/lib/teachingEvent/calendar.utils';
import CalendarEventCard from '@/components/teachingEvents/calendar/event-card';

interface CalendarProps {
  selectedEvents: EventModel[];
}

export default function Calendar(props: CalendarProps) {
  const timeIntervals = getTimeIntervals(8, 20);
  const weekDays = ['Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag'];
  return (
    <>
      <div className={'container'}>
        <div
          className={
            '-z-10 relative grid grid-cols-[auto,1fr,1fr,1fr,1fr,1fr] grid-rows-[auto_repeat(52,minmax(40px,1fr))] gap-x-4 text-left text-xl text-muted-foreground'
          }
        >
          <div className="w-16"></div>
          {weekDays.map((weekDay, index) => (
            <div key={index} className={'pb-4'}>{weekDay}</div>
          ))}
          {timeIntervals.map((time) => (
              <div key={time} className={' col-start-1'}>
                {time.endsWith('00') && (
                    <>
                  <div className="absolute w-full border-t border-muted"></div>
                  <span
                    className={'text-sm tabular-nums text-muted-foreground'}
                  >
                    {time}
                  </span>
                    </>
                )}
              </div>
          ))}
          {props.selectedEvents.map((event, index) => (
            <div
              key={index}
              className={`z-0 ${getEventPosition(event)}`}
            >
              <CalendarEventCard event={event}></CalendarEventCard>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}

