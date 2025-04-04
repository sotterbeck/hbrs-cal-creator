import { SemesterFilter } from '@/components/teachingEvents/select/filter/semester-filter';

interface FilterPaneProps {
  semesterNames: string[];
}

export async function FilterPane({ semesterNames }: Readonly<FilterPaneProps>) {
  return (
    <div className="space-y-4">
      <SemesterFilter semesters={semesterNames} />
    </div>
  );
}
