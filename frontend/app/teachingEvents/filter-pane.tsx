import { SelectedSemestersParams } from '@/lib/selectedSemestersParams';
import { SemesterFilter } from '@/app/teachingEvents/semester-filter';
import { fetchSemesterNames } from '@/lib/data';

interface FilterProps {
  searchParams: SelectedSemestersParams;
  className?: string;
}

export async function FilterPane({ searchParams }: FilterProps) {
  let semesterNames = await fetchSemesterNames();
  return (
    <>
      <h2 className="mb-2 text-xl font-bold">Filter</h2>
      <SemesterFilter semesters={semesterNames.data} />
    </>
  );
}
