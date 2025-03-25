import {
  fetchSemesterNames,
  fetchTeachingEventsFromSemesters,
} from '@/lib/api/data';
import {
  getSelectedSemesters,
  SelectedSemestersParams,
} from '@/lib/semester/selectedSemestersParams';
import TeachingEventChooser from '@/components/teachingEvents/teaching-event-chooser';

export default async function Page({
  searchParams,
}: Readonly<{
  searchParams: SelectedSemestersParams;
}>) {
  const params = await searchParams;
  const teachingEvents = await fetchTeachingEventsFromSemesters(
    getSelectedSemesters(params),
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
