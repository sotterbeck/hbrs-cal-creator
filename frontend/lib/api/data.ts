const API_URL = getApiUrl();

export function getApiUrl(): string {
  return process.env.API_URL ?? 'http://localhost:8080';
}

/**
 * Mapping of semester names that need to be rewritten before sending them to the API.
 */
const semesterRewrites: Record<string, string> = {
  Sprachkurse1: 'Sprachkurse',
};

export async function fetchCoursesOfStudies(): Promise<CoursesOfStudyResponse> {
  const res = await fetch(`${API_URL}/api/coursesOfStudy`);
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

  const rewrittenSemesterIds = semesters.map(
    (semester) => semesterRewrites[semester] ?? semester,
  );

  const params = new URLSearchParams();

  rewrittenSemesterIds.forEach((id) => {
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
