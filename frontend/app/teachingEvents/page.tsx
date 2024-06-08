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
  const teachingEventResponse = await fetchTeachingEventsFromSemesters(
    getSelectedSemesters(searchParams),
  );

  return (
    <div className="flex">
      <aside className="h-dvh min-w-72 border-r bg-white px-6 py-3">
        <FilterPane searchParams={searchParams} />
      </aside>
      <main className="w-full p-4">
        <TeachingEventTable teachingEvents={teachingEventResponse.data} />
      </main>
    </div>
  );
}
