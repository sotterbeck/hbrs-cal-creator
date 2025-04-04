'use client';
import React, { useState } from 'react';
import TeachingEventsHeader from '@/components/teachingEvents/teaching-events-header';
import ExportButton from '@/components/teachingEvents/export-button';
import { useTeachingEventSearching } from '@/lib/teachingEvent/useTeachingEventSearching';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import TeachingEventsSelect from '@/components/teachingEvents/select/teaching-events-select';
import Calendar from '@/components/teachingEvents/calendar/calendar';

interface TeachingEventChooserProps {
  teachingEvents: EventModel[];
}

export type SelectedEventsState = Record<string, boolean>;

export default function TeachingEvents({
  teachingEvents,
}: Readonly<TeachingEventChooserProps>) {
  const [selectedEvents, setSelectedEvents] = useState<SelectedEventsState>({});
  const [searchQuery, setSearchQuery] = useState<string>("");

  const searchFn = useTeachingEventSearching();
  const filteredTeachingEvents = searchFn.search(searchQuery, teachingEvents);
  const selectedEventModels = teachingEvents.filter(({ id }) =>
    Object.keys(selectedEvents).includes(id),
  );

  return (
    <>
      <TeachingEventsHeader selectedEvents={selectedEvents} />
      <main>
        <Tabs className={'flex flex-col container'} defaultValue={'select'}>
          <TabsList className={'mt-4 self-center'}>
            <TabsTrigger value={'select'}>Auswahl</TabsTrigger>
            <TabsTrigger value={'calendar'}>Kalender</TabsTrigger>
          </TabsList>
          <TabsContent value={'select'}>
            <TeachingEventsSelect
              teachingEvents={filteredTeachingEvents}
              selectedEvents={selectedEvents}
              setSelectedEvents={setSelectedEvents}
              searchQuery={searchQuery}
              setSearchQuery={setSearchQuery}
            />
          </TabsContent>
          <TabsContent value={'calendar'}>
            <Calendar selectedEvents={selectedEventModels}></Calendar>
          </TabsContent>
        </Tabs>
      </main>
      <div className="fixed bottom-0 left-0 right-0 bg-gradient-to-t from-zinc-50 py-6 dark:from-background md:invisible">
        <div className="container flex justify-end">
          <ExportButton selectedEvents={selectedEvents} className="w-full" />
        </div>
      </div>
    </>
  );
}
