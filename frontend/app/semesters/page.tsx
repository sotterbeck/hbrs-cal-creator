import {
  Breadcrumb,
  BreadcrumbItem,
  BreadcrumbLink,
  BreadcrumbList,
  BreadcrumbPage,
  BreadcrumbSeparator,
} from '@/components/ui/breadcrumb';
import Link from 'next/link';
import { fetchCoursesOfStudies } from '@/lib/data';
import { SemesterGrid } from '@/app/semesters/semester-grid';
import { Button } from '@/components/ui/button';
import {
  getSelectedSemestersCount,
  SelectedSemestersParams,
} from '@/lib/selectedSemestersParams';

export default async function Page({
  searchParams,
}: {
  searchParams: SelectedSemestersParams;
}) {
  const coursesOfStudiesResponse = await fetchCoursesOfStudies();
  const selectedSemestersCount = getSelectedSemestersCount(searchParams);

  return (
    <main className="container mx-auto space-y-6 py-10">
      <h1 className="scroll-m-20 text-4xl font-extrabold tracking-tight lg:text-5xl">
        Wähle deine Studiengänge
      </h1>
      <Breadcrumb>
        <BreadcrumbList>
          <BreadcrumbItem>
            <BreadcrumbLink asChild>
              <Link href="/">Home</Link>
            </BreadcrumbLink>
          </BreadcrumbItem>
          <BreadcrumbSeparator />
          <BreadcrumbItem>
            <BreadcrumbPage>Semester</BreadcrumbPage>
          </BreadcrumbItem>
        </BreadcrumbList>
      </Breadcrumb>
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
