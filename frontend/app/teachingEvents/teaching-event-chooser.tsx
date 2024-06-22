'use client';
import TeachingEventList from '@/app/teachingEvents/teaching-event-list';
import { useState } from 'react';
import TeachingEventsHeader from '@/app/teachingEvents/teaching-events-header';
import ExportButton from '@/app/teachingEvents/export-button';
import { useTeachingEventSearching } from '@/lib/teachingEvent/useTeachingEventSearching';
import { TeachingEventTable } from '@/app/teachingEvents/teaching-event-table';

interface TeachingEventChooserProps {
  teachingEvents: EventModel[];
  semesterNames: string[];
}

export type SelectedEventsState = Record<string, boolean>;

export default function TeachingEventChooser({
  teachingEvents,
  semesterNames,
}: Readonly<TeachingEventChooserProps>) {
  const [selectedEvents, setSelectedEvents] = useState<SelectedEventsState>({});
  const [searchQuery, setSearchQuery] = useState<string>();

  const searchFn = useTeachingEventSearching();

  const filteredTeachingEvents = searchFn.search(searchQuery, teachingEvents);

  return (
    <>
      <TeachingEventsHeader
        selectedEvents={selectedEvents}
        semesterNames={semesterNames}
        searchQuery={searchQuery}
        setSearchQuery={setSearchQuery}
      />
      <main>
        <TeachingEventList
          teachingEvents={filteredTeachingEvents}
          selectedEvents={selectedEvents}
          setSelectedEvents={setSelectedEvents}
          className="pb-20 lg:hidden"
        />
        <TeachingEventTable
          teachingEvents={filteredTeachingEvents}
          rowSelection={selectedEvents}
          setRowSelection={setSelectedEvents}
          className="container hidden lg:block"
        />
        <div className="fixed bottom-0 left-0 right-0 bg-gradient-to-t from-zinc-50 py-6 dark:from-background md:invisible">
          <div className="container flex justify-end">
            <ExportButton selectedEvents={selectedEvents} className="w-full" />
          </div>
        </div>
      </main>
    </>
  );
}
