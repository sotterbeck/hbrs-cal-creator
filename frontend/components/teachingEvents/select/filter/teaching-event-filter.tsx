import React, { Dispatch, SetStateAction } from 'react';
import { Input } from '@/components/ui/input';
import {
  Sheet,
  SheetContent,
  SheetDescription,
  SheetHeader,
  SheetTitle,
  SheetTrigger,
} from '@/components/ui/sheet';
import { Button } from '@/components/ui/button';
import { ListFilter } from 'lucide-react';

interface TeachingEventFilterProps {
  searchQuery: string;
  setSearchQuery: Dispatch<SetStateAction<string>>;
}

export default function TeachingEventFilter(props: TeachingEventFilterProps) {
  function handleSearchQueryInput(event: React.ChangeEvent<HTMLInputElement>) {
    props.setSearchQuery(event.target.value);
  }

  return (
    <>
      <div className="bg-zinc-50/50 dark:bg-black/50">
        <div className="flex w-full items-center space-x-2 py-4">
          <Input
            type="text"
            placeholder="Search ..."
            className="w-full"
            value={props.searchQuery}
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
            </SheetContent>
          </Sheet>
        </div>
      </div>
    </>
  );
}
