import TeachingEventCard from '@/components/teachingEvents/select/mobile-list/teaching-event-card';
import React from 'react';
import { twMerge } from 'tailwind-merge';
import { SelectedEventsState } from '@/components/teachingEvents/teaching-events';

interface TeachingEventListProps {
  teachingEvents: EventModel[];
  selectedEvents: SelectedEventsState;
  setSelectedEvents: React.Dispatch<React.SetStateAction<SelectedEventsState>>;
  className?: string;
}

export default function TeachingEventList({
  teachingEvents,
  selectedEvents,
  setSelectedEvents,
  className,
}: Readonly<TeachingEventListProps>) {
  function handleSelect(selected: boolean, id: string) {
    setSelectedEvents((prev) => {
      return {
        ...prev,
        [id]: selected,
      };
    });
  }

  return (
    <div className={twMerge('space-y-2', className)}>
      {teachingEvents.length === 0 && (
        <p className="pt-6 text-center text-muted-foreground">
          Keine Veranstaltungen gefunden.
        </p>
      )}
      {teachingEvents.map((teachingEvent) => {
        return (
          <TeachingEventCard
            id={teachingEvent.id}
            title={teachingEvent.module}
            instructor={teachingEvent.instructor}
            time={`${teachingEvent.day}. ${teachingEvent.startTime}-${teachingEvent.endTime}`}
            types={teachingEvent.types.map((type) => type.name)}
            group={teachingEvent.group}
            key={teachingEvent.id}
            setSelected={handleSelect}
            selected={selectedEvents[teachingEvent.id]}
          />
        );
      })}
    </div>
  );
}
