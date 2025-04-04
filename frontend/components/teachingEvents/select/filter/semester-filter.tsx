'use client';

import { Checkbox } from '@/components/ui/checkbox';
import {
  addSemesterToParams,
  getSelectedSemesters,
  removeSemesterFromParams,
} from '@/lib/semester/selectedSemestersParams';
import { Button } from '@/components/ui/button';
import { useSearchParamsManipulation } from '@/lib/common/useSearchParamsManipulation';
import { useState } from 'react';
import { NavigateOptions } from 'next/dist/shared/lib/app-router-context.shared-runtime';

interface SemesterSelectionProps {
  semesters: string[];
}

interface SemesterCheckboxProps {
  semester: string;
  selectedSemesterIds: string[];
  searchParams: URLSearchParams;
  pathname: string;
  replace: (path: string, option: NavigateOptions) => void;
}

export function SemesterFilter({
  semesters,
}: Readonly<SemesterSelectionProps>) {
  const { readonlyURLSearchParams, pathname, replace } =
    useSearchParamsManipulation();

  const [showMore, setShowMore] = useState(false);

  const selectedSemesterIds = getSelectedSemesters(readonlyURLSearchParams);
  if (!showMore) {
    semesters = semesters.slice(0, 5);
  }

  function handleShowMoreToggle() {
    setShowMore(!showMore);
  }

  return (
    <section className="space-y-2">
      <p className="font-medium">Semester</p>
      {semesters.map((semester) => (
        <SemesterCheckbox
          key={semester.replace(' ', '')}
          semester={semester}
          selectedSemesterIds={selectedSemesterIds}
          searchParams={readonlyURLSearchParams}
          pathname={pathname}
          replace={replace}
        />
      ))}
      <Button
        variant="link"
        size="sm"
        className="text-muted-foreground"
        onClick={handleShowMoreToggle}
      >
        {showMore ? 'Weniger Anzeigen' : 'Mehr Anzeigen'}
      </Button>
    </section>
  );
}

function SemesterCheckbox({
  semester,
  selectedSemesterIds,
  searchParams,
  pathname,
  replace,
}: Readonly<SemesterCheckboxProps>) {
  const semesterToken = semester.replace(' ', '');

  function handleCheckedChange(checked: boolean) {
    const params = checked
      ? addSemesterToParams(searchParams, semesterToken)
      : removeSemesterFromParams(searchParams, semesterToken);

    replace(`${pathname}?${params.toString()}`, { scroll: false });
  }

  return (
    <div className="flex items-center space-x-2">
      <Checkbox
        id={semesterToken}
        defaultChecked={selectedSemesterIds.includes(semesterToken)}
        onCheckedChange={handleCheckedChange}
      />
      <label
        htmlFor={semesterToken}
        className="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
      >
        {semester}
      </label>
    </div>
  );
}
