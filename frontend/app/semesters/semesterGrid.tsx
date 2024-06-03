import { CourseOfStudy } from '@/lib/data';
import { SemesterCard } from '@/app/semesters/semesterCard';

export function SemesterGrid({ courses }: { courses: CourseOfStudy[] }) {
  return (
    <div className="grid grid-cols-1 gap-4 md:grid-cols-3  xl:grid-cols-4">
      {courses.map((course: CourseOfStudy) => {
        return <SemesterCard key={course.abbreviation} course={course} />;
      })}
    </div>
  );
}
