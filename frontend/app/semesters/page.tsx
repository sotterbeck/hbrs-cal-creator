import Link from 'next/link';
import { fetchCoursesOfStudies } from '@/lib/data';
import { SemesterGrid } from '@/app/semesters/semester-grid';
import { Button } from '@/components/ui/button';
import {
  getSelectedSemestersCount,
  SelectedSemestersParams,
} from '@/lib/selectedSemestersParams';
import Title from '@/app/semesters/header';

export default async function Page({
  searchParams,
}: {
  searchParams: SelectedSemestersParams;
}) {
  const coursesOfStudiesResponse = await fetchCoursesOfStudies();
  const selectedSemestersCount = getSelectedSemestersCount(searchParams);

  return (
    <main className="container space-y-6 py-6">
      <Title />
      <SemesterGrid courses={coursesOfStudiesResponse.data} />
      <div className="flex items-center justify-between">
        <p>{selectedSemestersCount} Studiengänge / Semester ausgewählt.</p>
        <Button variant="default" asChild>
          <Link
            href={{
              pathname: '/teachingEvents',
              query: searchParams,
            }}
          >
            Weiter
          </Link>
        </Button>
      </div>
    </main>
  );
}
