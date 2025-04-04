import {
  getCalendarData,
  getTimeIntervals,
} from '@/lib/teachingEvent/calendar.utils';
import CalendarEventCard from '@/components/teachingEvents/calendar/event-card';

interface CalendarProps {
  selectedEvents: EventModel[];
}

export default function Calendar(props: CalendarProps) {
  const timeIntervals = getTimeIntervals(8, 20);
  const calendarData = getCalendarData(props.selectedEvents);
  return (
    <>
      <div
        className={
          'grid-rows-[auto_repeat(52,minmax(1fr,30px)] my-10 grid w-full grid-cols-[auto,1fr,1fr,1fr,1fr,1fr] overflow-x-auto border-r pb-2 text-left text-xl text-muted-foreground'
        }
      >
        <div className={'col-span-6 col-start-2 grid grid-cols-subgrid'}>
          {calendarData.map((data, index) => (
            <div key={index} className={'pb-2 pl-2'}>
              {data.name}
            </div>
          ))}
        </div>
        <div
          className={
            'sticky left-0 col-start-1 row-span-54 row-start-1 grid grid-rows-subgrid border-r bg-zinc-50 pr-4 dark:bg-black'
          }
        >
          <div></div>
          {timeIntervals.map((time, index) => (
            <div key={index} className={'text-sm tabular-nums'}>
              {time.endsWith('00') ? (
                <p className={'-translate-y-2.5'}>{time}</p>
              ) : (
                <p className={'invisible'}>{time}</p>
              )}
            </div>
          ))}
        </div>
        <div
          className={
            'col-span-6 col-start-1 row-span-54 row-start-2 grid grid-cols-subgrid grid-rows-subgrid'
          }
        >
          {timeIntervals.map((time, index) =>
            time.endsWith('00') ? (
              <div
                key={index}
                className={`col-span-6 col-start-2 border-t border-muted-foreground/60`}
              ></div>
            ) : (
              <div
                key={index}
                className={`col-span-6 col-start-2 border-t`}
              ></div>
            ),
          )}
        </div>
        {calendarData.map((data, index) => (
          <div
            key={index}
            className={`${positionStylesLookup['colStart'][index + 2]} row-span-54 row-start-2 grid grid-rows-subgrid gap-x-2 border-r px-4`}
          >
            {data.events.map((event, index) => (
              <div
                key={index}
                className={`${positionStylesLookup['rowStart'][data.events[index].position.rowStart]} ${positionStylesLookup['rowSpan'][data.events[index].position.rowSpan]}`}
              >
                <CalendarEventCard event={event}></CalendarEventCard>
              </div>
            ))}
          </div>
        ))}
      </div>
    </>
  );
}

export const positionStylesLookup: { [index: string]: any } = {
  rowStart: {
    1: 'row-start-1',
    2: 'row-start-2',
    3: 'row-start-3',
    4: 'row-start-4',
    5: 'row-start-5',
    6: 'row-start-6',
    7: 'row-start-7',
    8: 'row-start-8',
    9: 'row-start-9',
    10: 'row-start-10',
    11: 'row-start-11',
    12: 'row-start-12',
    13: 'row-start-13',
    14: 'row-start-14',
    15: 'row-start-15',
    16: 'row-start-16',
    17: 'row-start-17',
    18: 'row-start-18',
    19: 'row-start-19',
    20: 'row-start-20',
    21: 'row-start-21',
    22: 'row-start-22',
    23: 'row-start-23',
    24: 'row-start-24',
    25: 'row-start-25',
    26: 'row-start-26',
    27: 'row-start-27',
    28: 'row-start-28',
    29: 'row-start-29',
    30: 'row-start-30',
    31: 'row-start-31',
    32: 'row-start-32',
    33: 'row-start-33',
    34: 'row-start-34',
    35: 'row-start-35',
    36: 'row-start-36',
    37: 'row-start-37',
    38: 'row-start-38',
    39: 'row-start-39',
    40: 'row-start-40',
    41: 'row-start-41',
    42: 'row-start-42',
    43: 'row-start-43',
    44: 'row-start-44',
    45: 'row-start-45',
    46: 'row-start-46',
    47: 'row-start-47',
    48: 'row-start-48',
    49: 'row-start-49',
    50: 'row-start-50',
    51: 'row-start-51',
    52: 'row-start-52',
  },
  rowSpan: {
    1: 'row-span-1',
    2: 'row-span-2',
    3: 'row-span-3',
    4: 'row-span-4',
    5: 'row-span-5',
    6: 'row-span-6',
    7: 'row-span-7',
    8: 'row-span-8',
    9: 'row-span-9',
    10: 'row-span-10',
    11: 'row-span-11',
    12: 'row-span-12',
    13: 'row-span-13',
    14: 'row-span-14',
    15: 'row-span-15',
    16: 'row-span-16',
    17: 'row-span-17',
    18: 'row-span-18',
    19: 'row-span-19',
    20: 'row-span-20',
    21: 'row-span-21',
    22: 'row-span-22',
    23: 'row-span-23',
    24: 'row-span-24',
  },
  colStart: {
    1: 'col-start-1',
    2: 'col-start-2',
    3: 'col-start-3',
    4: 'col-start-4',
    5: 'col-start-5',
    6: 'col-start-6',
  },
};
