import { SemesterCard } from '@/app/semesters/semester-card';

export function SemesterGrid({ courses }: { courses: CourseOfStudyModel[] }) {
  return (
    <div className="grid grid-cols-1 gap-4 md:grid-cols-3  xl:grid-cols-4">
      {courses.map((course: CourseOfStudyModel) => {
        return <SemesterCard key={course.abbreviation} course={course} />;
      })}
    </div>
  );
}
