import { fetchCoursesOfStudies } from '@/lib/api/data';
import { SemesterGrid } from '@/components/semesters/semester-grid';
import {
  getSelectedSemestersCount,
  SelectedSemestersParams,
} from '@/lib/semester/selectedSemestersParams';
import SemesterHeaderBar from '@/components/semesters/semester-header-bar';
import ContinueButton from '@/components/semesters/continue-button';

export default async function Page({
  searchParams,
}: Readonly<{
  searchParams: SelectedSemestersParams;
}>) {
  const params = await searchParams;
  const coursesOfStudiesResponse = await fetchCoursesOfStudies();
  const selectedSemestersCount = getSelectedSemestersCount(params);

  return (
    <>
      <SemesterHeaderBar numberOfSelectedSemesters={selectedSemestersCount} />
      <main>
        <SemesterGrid
          courses={coursesOfStudiesResponse.data}
          className="container pt-6"
        />
        <div className="sticky bottom-0 left-0 right-0 bg-gradient-to-t from-zinc-50 py-6 dark:from-background md:invisible">
          <div className="container flex justify-end">
            <ContinueButton />
          </div>
        </div>
      </main>
    </>
  );
}
