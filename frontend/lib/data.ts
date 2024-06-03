// TODO: Implement the functions below to fetch data from the API and update the types accordingly

export interface CourseOfStudy {
  name: string;
  abbreviation: string;
  semesters: number[];
}

export interface TeachingEvent {
  id: string;
  semesterId: String;
  title: string;
  time: string;
  room: string;
  lecturer: string;
  types: TeachingEventType[];
  group: string | null;
  info: string;
}

export enum TeachingEventType {
  LECTURE = 'Vorlesung',
  EXERCISE = 'Übung',
  TUTORIAL = 'Tutorium',
  SEMINAR = 'Seminar',
  PRACTICAL = 'Praktikum',
}

export interface Semester {
  name: string;
  id: string;
}

export async function fetchCoursesOfStudies(): Promise<CourseOfStudy[]> {
  return [];
}

export async function fetchTeachingEventsFromSemesters(
  semesters: string[],
): Promise<TeachingEvent[]> {
  return [];
}

export async function fetchSemesterNames(): Promise<Semester[]> {
  return [];
}
