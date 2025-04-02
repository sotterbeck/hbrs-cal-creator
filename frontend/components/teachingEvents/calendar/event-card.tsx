import {Card, CardContent, CardHeader, CardTitle} from "@/components/ui/card";
import {getEventCardBorderStyles} from "@/lib/teachingEvent/calendar.utils";

interface CalendarEventCardProps {
    event: EventModel;
}

export default function CalendarEventCard(props: CalendarEventCardProps) {
    return (
        <>
            <Card className={`h-full bg-card ${getEventCardBorderStyles(props.event.types)}`}>
                <CardHeader>
                    <CardTitle>{props.event.title}</CardTitle>
                </CardHeader>
                <CardContent className={'text-muted-foreground'}>
                    <p>{props.event.startTime} - {props.event.endTime}</p>
                    <p>{props.event.room}</p>
                    <p>{props.event.group}</p>
                    <p>{props.event.instructor}</p>
                </CardContent>
            </Card>
        </>
    )
}

export const borderStyleLookup: { [index: string]: any } = {
    v: 'border-l-4 border-l-primary',
    ue: 'border-l-4 border-l-accent2',
    both: 'border-l-4 border-l-accent3',
};