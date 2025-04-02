import {getEventCardStyle} from "@/lib/teachingEvent/calendar.utils";

interface CalendarEventCardProps {
    event: EventModel;
}

export default function CalendarEventCard(props: CalendarEventCardProps) {
    return (
        <>
            <div className={`rounded p-2 ${getEventCardStyle(props.event.types)}`}>
                <p className={'text-xs font-bold'}>{props.event.title}</p>
                <p className={'text-xs'}>{props.event.startTime} - {props.event.endTime}</p>
                <p className={'text-xs'}>{props.event.room}</p>
                <p className={'text-xs'}>{props.event.group}</p>
                <p className={'text-xs'}>{props.event.instructor}</p>
            </div>
        </>
    )
}

export const cardStyleLookup: { [index: string]: any } = {
    v: 'border-l-4 border-l-primary bg-primary-foreground',
    ue: 'border-l-4 border-l-accent2 bg-accent2-foreground',
    both: 'border-l-4 border-l-accent3 bg-accent3-foreground',
};