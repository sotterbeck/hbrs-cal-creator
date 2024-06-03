import {
  getSelectedSemesters,
  SelectedSemestersParams,
} from '@/lib/selectedSemestersParams';
import { cn } from '@/lib/utils';
import { SemesterFilter } from '@/app/teachingEvents/semesterFilter';
import { fetchSemesterNames } from '@/lib/data';

interface FilterProps {
  searchParams: SelectedSemestersParams;
  className?: string;
}

export async function FilterPane({ searchParams }: FilterProps) {
  let semesterNames = await fetchSemesterNames();
  return (
    <>
      <h2 className="text-xl font-bold mb-2">Filter</h2>
      <SemesterFilter semesters={semesterNames} />
    </>
  );
}
