import { SemesterCard } from '@/components/semesters/semester-card';
import { twMerge } from 'tailwind-merge';

interface SemesterGridProps {
  courses: CourseOfStudyModel[];
  className?: string;
}

export function SemesterGrid({ courses, className }: SemesterGridProps) {
  return (
    <div
      className={twMerge(
        'grid grid-cols-1 gap-4 md:grid-cols-3  xl:grid-cols-4',
        className,
      )}
    >
      {courses.map((course: CourseOfStudyModel) => {
        return <SemesterCard key={course.abbreviation} course={course} />;
      })}
    </div>
  );
}
