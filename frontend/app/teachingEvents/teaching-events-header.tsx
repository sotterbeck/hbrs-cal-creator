import HeaderBar from '@/components/ui/header-bar';
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import { ListFilter } from 'lucide-react';
import { ModeToggle } from '@/components/ui/mode-toggle';
import ExportButton from '@/app/teachingEvents/export-button';
import {
  Sheet,
  SheetContent,
  SheetDescription,
  SheetHeader,
  SheetTitle,
  SheetTrigger,
} from '@/components/ui/sheet';
import { FilterPane } from '@/app/teachingEvents/filter-pane';
import { Dispatch, SetStateAction, Suspense } from 'react';
import { Spinner } from '@/components/ui/spinner';
import { SelectedEventsState } from '@/app/teachingEvents/teaching-event-chooser';

interface TeachingEventsHeaderProps {
  selectedEvents: SelectedEventsState;
  semesterNames: string[];
  searchQuery: string | undefined;
  setSearchQuery: Dispatch<SetStateAction<string | undefined>>;
}

export default function TeachingEventsHeader({
  selectedEvents,
  semesterNames,
  searchQuery,
  setSearchQuery,
}: Readonly<TeachingEventsHeaderProps>) {
  function handleSearchQueryInput(event: React.ChangeEvent<HTMLInputElement>) {
    setSearchQuery(event.target.value);
  }

  let numberOfSelectedEvents =
    Object.values(selectedEvents).filter(Boolean).length;

  return (
    <div className="sticky top-0 z-10 backdrop-blur">
      <HeaderBar
        title={'Wähle deine Veranstaltungen'}
        description={`${numberOfSelectedEvents} Veranstaltungen ausgewählt`}
        className="backdrop-blur-none"
      >
        <div className="flex gap-2">
          <ExportButton
            selectedEvents={selectedEvents}
            className="hidden md:inline-flex"
          />
          <ModeToggle />
        </div>
      </HeaderBar>
      <div className="bg-zinc-50/50 dark:bg-black/50">
        <div className="container">
          <div className="flex w-full items-center space-x-2 py-4">
            <Input
              type="text"
              placeholder="Search ..."
              className="w-full"
              value={searchQuery}
              onInput={handleSearchQueryInput}
            />
            <Sheet>
              <SheetTrigger asChild>
                <Button variant="outline" size="icon" className="min-w-10">
                  <ListFilter />
                </Button>
              </SheetTrigger>
              <SheetContent className="overflow-scroll">
                <SheetHeader>
                  <SheetTitle className="text-left">Filter</SheetTitle>
                </SheetHeader>
                <SheetDescription className="mb-6">
                  Filtere die Lehrveranstaltungen nach deinen Wünschen.
                </SheetDescription>
                <Suspense fallback={<Spinner text={'Lade Filter'} />}>
                  <FilterPane semesterNames={semesterNames} />
                </Suspense>
              </SheetContent>
            </Sheet>
          </div>
        </div>
      </div>
    </div>
  );
}
