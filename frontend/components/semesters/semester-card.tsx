'use client';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';
import { ToggleGroup, ToggleGroupItem } from '@/components/ui/toggle-group';
import {
  ReadonlyURLSearchParams,
  usePathname,
  useRouter,
  useSearchParams,
} from 'next/navigation';
import { getSelectedSemesters } from '@/lib/semester/selectedSemestersParams';
import {
  normalizeSemesterToken,
  parseSemesterLabel,
} from '@/lib/semester/semesterLabel';
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from '@/components/ui/tooltip';
import { cn } from '@/lib/utils';

export function SemesterCard({
  course,
}: Readonly<{
  course: CourseOfStudyModel;
  onSemestersChange?: (semesters: string[]) => void;
  semestersValue?: string[];
}>) {
  const readOnlySearchParams = useSearchParams();
  const pathname = usePathname();
  const { replace } = useRouter();

  function handleValueChange(semesters: string[]) {
    const params = updateSelectedSemestersInParams(
      readOnlySearchParams,
      course.abbreviation,
      semesters,
    );

    replace(`${pathname}?${params.toString()}`, { scroll: false });
  }

  return (
    <Card>
      <CardHeader className="h-32 overflow-hidden rounded-t-lg bg-accent text-center">
        <CardTitle>{course.abbreviation}</CardTitle>
        <CardDescription>{course.name}</CardDescription>
      </CardHeader>
      <CardContent className="space-y-1 pt-4">
        <p className="text-foreground">Semester</p>
        <ToggleGroup
          type="multiple"
          variant="outlinePrimary"
          className="justify-start"
          onValueChange={handleValueChange}
          defaultValue={getSelectedCourseSemesters(
            course.abbreviation,
            readOnlySearchParams,
          )}
        >
          <TooltipProvider>
            {course.semesters.map((semester) => {
              const semesterValue = normalizeSemesterToken(semester);
              const { displayLabel, poYear } = parseSemesterLabel(semester);
              const isOldPo = Boolean(poYear);
              if (!isOldPo) {
                return (
                  <ToggleGroupItem key={semesterValue} value={semesterValue}>
                    {displayLabel}
                  </ToggleGroupItem>
                );
              }

              return (
                <ToggleGroupItem
                  key={semesterValue}
                  value={semesterValue}
                  className={cn('text-amber-600 dark:text-amber-400')}
                >
                  <Tooltip>
                    <TooltipTrigger asChild>
                      <span className="inline-flex h-full w-full items-center justify-center">
                        {displayLabel}
                      </span>
                    </TooltipTrigger>
                    <TooltipContent>
                      <p>PO {poYear}</p>
                    </TooltipContent>
                  </Tooltip>
                </ToggleGroupItem>
              );
            })}
          </TooltipProvider>
        </ToggleGroup>
      </CardContent>
    </Card>
  );
}

function getCourseIds(abbreviation: string, semesters: string[]) {
  return semesters.map((semester) =>
    normalizeSemesterToken(`${abbreviation}${semester}`),
  );
}

function getSelectedCourseSemesters(
  abbreviation: string,
  searchParams: URLSearchParams,
) {
  const selectedSemesters = getSelectedSemesters(searchParams);
  if (selectedSemesters.length === 0) {
    return [];
  }

  return selectedSemesters
    .filter((semester) => semester.startsWith(abbreviation))
    .map((semester) =>
      normalizeSemesterToken(semester.replace(abbreviation, '').trim()),
    );
}

function updateSelectedSemestersInParams(
  searchParams: ReadonlyURLSearchParams,
  abbreviation: string,
  semesters: string[],
) {
  const params = new URLSearchParams(searchParams);

  const selectedSemesters = getSelectedSemesters(params);
  const selectedSemestersWithoutCurrentCourseOfStudy = selectedSemesters.filter(
    (courseId) => !courseId.startsWith(abbreviation),
  );

  const newSemesterIds = getCourseIds(abbreviation, semesters);
  const newSelectedSemesters = [
    ...selectedSemestersWithoutCurrentCourseOfStudy,
    ...newSemesterIds,
  ].sort((a, b) => a.localeCompare(b));

  params.delete('semester');
  newSelectedSemesters.forEach((semester) => {
    params.append('semester', semester);
  });

  return params;
}
