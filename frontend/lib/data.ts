export async function fetchCoursesOfStudies(): Promise<CoursesOfStudyResponse> {
  const res = await fetch('http://localhost:8080/api/coursesOfStudy');
  if (!res.ok) {
    throw new Error('Failed to fetch courses of study');
  }
  return res.json();
}

export async function fetchTeachingEventsFromSemesters(
  semesters: string[],
): Promise<TeachingEventResponse> {
  if (semesters.length === 0) {
    return { data: [] };
  }

  const params = new URLSearchParams();

  semesters.forEach((semester) => {
    params.append('semester', semester);
  });

  const res = await fetch(
    `http://localhost:8080/api/teachingEvents?${params.toString()}`,
  );

  if (!res.ok) {
    throw new Error('Failed to fetch teaching events');
  }

  return res.json();
}

export async function fetchSemesterNames(): Promise<SemesterNamesResponse> {
  const res = await fetch('http://localhost:8080/api/coursesOfStudy/names');
  if (!res.ok) {
    throw new Error('Failed to fetch semester names');
  }
  return res.json();
}
