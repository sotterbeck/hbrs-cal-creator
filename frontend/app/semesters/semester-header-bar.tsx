import HeaderBar from '@/components/ui/header-bar';
import { ModeToggle } from '@/components/ui/mode-toggle';
import ContinueButton from '@/app/semesters/continue-button';

export interface SemesterHeaderBarProps {
  numberOfSelectedSemesters: number;
}

export default function SemesterHeaderBar({
  numberOfSelectedSemesters,
}: SemesterHeaderBarProps) {
  return (
    <HeaderBar
      title={'Wähle deine Semester'}
      description={`${numberOfSelectedSemesters} Semester ausgewählt`}
    >
      <div className="flex flex-row gap-2">
        <ContinueButton className="hidden md:inline-flex" />
        <ModeToggle />
      </div>
    </HeaderBar>
  );
}
