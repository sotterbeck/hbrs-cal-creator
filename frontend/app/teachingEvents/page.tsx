import {
  fetchSemesterNames,
  fetchTeachingEventsFromSemesters,
} from '@/lib/api/data';
import {
  getSelectedSemesters,
  resolveParams,
  SelectedSemestersParams,
} from '@/lib/semester/selectedSemestersParams';
import TeachingEventChooser from '@/components/teachingEvents/teaching-event-chooser';

export default async function Page(props: {
  searchParams: Promise<SelectedSemestersParams>;
}) {
  const searchParams = await resolveParams(props.searchParams);
  const teachingEvents = await fetchTeachingEventsFromSemesters(
    getSelectedSemesters(searchParams),
  ).then((response) => response.data);

  const semesterNames = await fetchSemesterNames().then(
    (response) => response.data,
  );

  return (
    <TeachingEventChooser
      teachingEvents={teachingEvents}
      semesterNames={semesterNames}
    />
  );
}
