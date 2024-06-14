import SemesterCardSkeleton from '@/app/semesters/semester-card-skeleton';
import SemesterHeaderBar from '@/app/semesters/semester-header-bar';

export default async function Page() {
  const numberOfCoursesOfStudies = 9;
  const courses = new Array(numberOfCoursesOfStudies).fill(null);

  return (
    <main>
      <SemesterHeaderBar numberOfSelectedSemesters={0} />
      <div className="container grid grid-cols-1 gap-4  pt-6 md:grid-cols-3 xl:grid-cols-4">
        {courses.map((_, index) => {
          return <SemesterCardSkeleton key={index} />;
        })}
      </div>
    </main>
  );
}
