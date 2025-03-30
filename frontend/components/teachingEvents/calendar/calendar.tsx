interface CalendarProps {
  selectedEvents: EventModel[];
}

export default function Calendar(props: CalendarProps) {
  return (
    <>
      <div className={'container grid grid-cols-6'}>
        <div className={'min-h-60 border-4'}>Zeit</div>
        <div className={'border-4'}>Montag</div>
        <div className={'border-4'}>Dienstag</div>
        <div className={'border-4'}>Mittwoch</div>
        <div className={'border-4'}>Donnerstag</div>
        <div className={'border-4'}>Freitag</div>
      </div>
    </>
  );
}
