import {
  fetchSemesterNames,
  fetchTeachingEventsFromSemesters,
} from '@/lib/api/data';
import {
  getSelectedSemesters,
  resolveParams,
  SelectedSemestersParams,
} from '@/lib/semester/selectedSemestersParams';
import TeachingEvents from '@/components/teachingEvents/teaching-events';

export default async function Page(props: {
  searchParams: Promise<SelectedSemestersParams>;
}) {
  const searchParams = await resolveParams(props.searchParams);
  const teachingEvents = await fetchTeachingEventsFromSemesters(
    getSelectedSemesters(searchParams),
  ).then((response) => response.data);

  return (
    <TeachingEvents
      teachingEvents={teachingEvents}
    />
  );
}
