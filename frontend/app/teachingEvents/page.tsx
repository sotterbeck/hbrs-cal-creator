import { fetchTeachingEventsFromSemesters } from '@/lib/data';
import {
  getSelectedSemesters,
  SelectedSemestersParams,
} from '@/lib/selectedSemestersParams';
import { FilterPane } from '@/app/teachingEvents/filter-pane';
import { TeachingEventTable } from '@/app/teachingEvents/teaching-event-table';

export default async function Page({
  searchParams,
}: {
  searchParams: SelectedSemestersParams;
}) {
  const teachingEvents = await fetchTeachingEventsFromSemesters(
    getSelectedSemesters(searchParams),
  );

  return (
    <div className="flex">
      <aside className="sticky top-0 h-dvh min-w-72 border-r bg-background px-6 py-4">
        <FilterPane searchParams={searchParams} />
      </aside>
      <main className="w-full p-4">
        <TeachingEventTable teachingEvents={teachingEvents.data} />
      </main>
    </div>
  );
}
