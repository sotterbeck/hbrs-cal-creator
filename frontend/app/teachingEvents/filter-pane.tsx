import { SelectedSemestersParams } from '@/lib/selectedSemestersParams';
import { SemesterFilter } from '@/app/teachingEvents/semester-filter';
import { fetchSemesterNames } from '@/lib/data';

interface FilterProps {
  searchParams: SelectedSemestersParams;
  className?: string;
}

export async function FilterPane({ searchParams }: FilterProps) {
  const semesterNames = await fetchSemesterNames();

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <h2 className="text-2xl font-bold">Filter</h2>
        <a className="text-sm text-muted-foreground">Clear</a>
      </div>
      <SemesterFilter semesters={semesterNames.data} />
    </div>
  );
}
