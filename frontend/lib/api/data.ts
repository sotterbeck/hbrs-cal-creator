import { isHiddenSemester } from '@/lib/semester/hiddenSemesters';

const API_URL = getApiUrl();

export function getApiUrl(): string {
  return process.env.API_URL ?? 'http://localhost:8080';
}

export async function fetchCoursesOfStudies(): Promise<CoursesOfStudyResponse> {
  const res = await fetch(`${API_URL}/api/coursesOfStudy`);
  if (!res.ok) {
    throw new Error('Failed to fetch courses of study');
  }

  const responseData: CoursesOfStudyResponse = await res.json();

  // Filter out courses that are blacklisted
  const filteredData = responseData.data.filter(
    (course) => !isHiddenSemester(course.name),
  );

  return { data: filteredData };
}

export async function fetchTeachingEventsFromSemesters(
  semesters: string[],
): Promise<TeachingEventResponse> {
  if (semesters.length === 0) {
    return { data: [] };
  }

  const params = new URLSearchParams();

  semesters.forEach((id) => {
    params.append('semester', id);
  });

  let url = `${API_URL}/api/teachingEvents?${params.toString()}`;
  const res = await fetch(url, { cache: 'no-store' });

  if (!res.ok) {
    throw new Error('Failed to fetch teaching events');
  }

  return res.json();
}

export async function fetchSemesterNames(): Promise<SemesterNamesResponse> {
  const res = await fetch(`${API_URL}/api/coursesOfStudy/names`);
  if (!res.ok) {
    throw new Error('Failed to fetch semester names');
  }
  return res.json();
}
