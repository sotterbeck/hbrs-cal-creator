import {
  fetchSemesterNames,
  fetchTeachingEventsFromSemesters,
} from '@/lib/api/data';
import {
  getSelectedSemesters,
  SelectedSemestersParams,
} from '@/lib/semester/selectedSemestersParams';
import TeachingEventChooser from '@/app/teachingEvents/teaching-event-chooser';

export default async function Page({
  searchParams,
}: Readonly<{
  searchParams: SelectedSemestersParams;
}>) {
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
