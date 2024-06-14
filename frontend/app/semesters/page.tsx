import { fetchCoursesOfStudies } from '@/lib/data';
import { SemesterGrid } from '@/app/semesters/semester-grid';
import {
  getSelectedSemestersCount,
  SelectedSemestersParams,
} from '@/lib/selectedSemestersParams';
import SemesterHeaderBar from '@/app/semesters/semester-header-bar';
import ContinueButton from '@/app/semesters/continue-button';

export default async function Page({
  searchParams,
}: {
  searchParams: SelectedSemestersParams;
}) {
  const coursesOfStudiesResponse = await fetchCoursesOfStudies();
  const selectedSemestersCount = getSelectedSemestersCount(searchParams);

  return (
    <>
      <SemesterHeaderBar numberOfSelectedSemesters={selectedSemestersCount} />
      <main>
        <SemesterGrid
          courses={coursesOfStudiesResponse.data}
          className="container pt-6"
        />
        <div className="sticky bottom-0 left-0 right-0 bg-gradient-to-t from-accent py-6 md:invisible">
          <div className="container flex justify-end">
            <ContinueButton />
          </div>
        </div>
      </main>
    </>
  );
}
