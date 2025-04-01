import {getEventPosition, getTimeIntervals} from '@/lib/teachingEvent/calendar.utils';
import CalendarEventCard from "@/components/teachingEvents/calendar/event-card";

interface CalendarProps {
    selectedEvents: EventModel[];
}

export default function Calendar(props: CalendarProps) {
    const timeIntervals = getTimeIntervals();
    const weekDays = ['Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag'];
    const eventsWithPos = props.selectedEvents.map(event => ({...event, positions: getEventPosition(event)}))
    console.log(eventsWithPos)
    const positionStyleMap: { [index: string]: any } = {
        rowStart: {
            1: "row-start-1",
            2: "row-start-2",
            3: "row-start-3",
            4: "row-start-4",
            5: "row-start-5",
            6: "row-start-6",
            7: "row-start-7",
            8: "row-start-8",
            9: "row-start-9",
            10: "row-start-10",
            11: "row-start-11",
            12: "row-start-12",
            13: "row-start-13",
            14: "row-start-14",
            15: "row-start-15",
            16: "row-start-16",
            17: "row-start-17",
            18: "row-start-18",
            19: "row-start-19",
            20: "row-start-20",
            21: "row-start-21",
            22: "row-start-22",
            23: "row-start-23",
            24: "row-start-24",
            25: "row-start-25",
            26: "row-start-26",
            27: "row-start-27",
            28: "row-start-28",
            29: "row-start-29",
            30: "row-start-30",
            31: "row-start-31",
            32: "row-start-32",
            33: "row-start-33",
            34: "row-start-34",
            35: "row-start-35",
            36: "row-start-36",
            37: "row-start-37",
            38: "row-start-38",
            39: "row-start-39",
            40: "row-start-40",
            41: "row-start-41",
            42: "row-start-42",
            43: "row-start-43",
            44: "row-start-44",
            45: "row-start-45",
            46: "row-start-46",
            47: "row-start-47",
            48: "row-start-48",
            49: "row-start-49",
            50: "row-start-50",
            51: "row-start-51",
            52: "row-start-52",
        },
        rowSpan: {
            1: "row-span-1",
            2: "row-span-2",
            3: "row-span-3",
            4: "row-span-4",
            5: "row-span-5",
            6: "row-span-6",
            7: "row-span-7",
            8: "row-span-8",
            9: "row-span-9",
            10: "row-span-10",
            11: "row-span-11",
            12: "row-span-12",
            13: "row-span-13",
            14: "row-span-14",
            15: "row-span-15",
            16: "row-span-16",
            17: "row-span-17",
            18: "row-span-18",
            19: "row-span-19",
            20: "row-span-20",
            21: "row-span-21",
            22: "row-span-22",
            23: "row-span-23",
            24: "row-span-24",
        },
        colStart: {
            2: "col-start-2",
            3: "col-start-3",
            4: "col-start-4",
            5: "col-start-5",
            6: "col-start-6",
        }
    }
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
                            <div key={time}
                                 className={'col-start-1 min-h-4 tabular-nums text-sm text-muted-foreground'}>
                                {time.endsWith('00') && <div>{time}</div>}
                            </div>
                    ))}
                    {eventsWithPos.map((event, index) => (
                        <div key={index}
                             className={`bg-blue-500 max-w-sm ${positionStyleMap.colStart[event.positions.colStart]} ${positionStyleMap.rowStart[event.positions.rowStart]} ${positionStyleMap.rowSpan[event.positions.rowSpan]}`}>
                            <CalendarEventCard event={event}></CalendarEventCard></div>
                    ))}
                </div>
            </div>
        </>
    )
        ;
}
