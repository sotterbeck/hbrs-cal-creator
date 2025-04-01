import {Card, CardContent, CardHeader, CardTitle} from "@/components/ui/card";

interface CalendarEventCardProps {
    event: EventModel;
}

export default function CalendarEventCard(props: CalendarEventCardProps) {
    return (
        <>
            <Card className={'h-full'}>
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