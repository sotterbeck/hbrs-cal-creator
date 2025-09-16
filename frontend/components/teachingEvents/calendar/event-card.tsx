import { getEventCardStyle } from '@/lib/teachingEvent/calendar.utils';

interface CalendarEventCardProps {
  event: EventModel;
}

export default function CalendarEventCard(props: CalendarEventCardProps) {
  return (
    <div
      className={`h-full min-w-full max-w-min text-wrap rounded p-2 md:w-auto ${cardStyleLookup[getEventCardStyle(props.event.types)] ?? cardStyleLookup['default']}`}
    >
      <p className={'text-xs font-bold'}>{props.event.title}</p>
      <p className={'text-xs'}>
        {props.event.startTime} - {props.event.endTime}
      </p>
      <p className={'text-xs'}>{props.event.room}</p>
      <p className={'text-xs'}>{props.event.instructor}</p>
    </div>
  );
}

export const cardStyleLookup: { [index: string]: string } = {
  default:
    'border-l-4 border-l-lime-950 bg-lime-400 text-lime-950 dark:border-l-lime-400 dark:bg-lime-950 dark:text-lime-400',
  v: 'border-l-4 border-l-teal-950 bg-teal-400 text-teal-950 dark:border-l-teal-400 dark:bg-teal-950 dark:text-teal-400',
  ue: 'border-l-4 border-l-pink-950 bg-pink-400 text-pink-950 dark:border-l-pink-400 dark:bg-pink-950 dark:text-pink-400',
  p: 'border-l-4 border-l-sky-950 bg-sky-400 text-sky-950 dark:border-l-sky-400 dark:bg-sky-950 dark:text-sky-400',
  both: 'border-l-4 border-l-amber-950 bg-amber-400 text-amber-950 dark:border-l-amber-400 dark:bg-amber-950 dark:text-amber-400',
};
