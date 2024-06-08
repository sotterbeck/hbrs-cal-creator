interface CoursesOfStudyResponse {
  data: CourseOfStudyModel[];
}

interface CourseOfStudyModel {
  name: string;
  abbreviation: string;
  semesters: number[];
}

interface SemesterNamesResponse {
  data: string[];
}

interface TeachingEventResponse {
  data: EventModel[];
}

interface EventModel {
  id: string;
  semester: string;
  title: string;
  module: string;
  day: string;
  startTime: string;
  endTime: string;
  room: string;
  instructor: string;
  group: string | null;
  types: EventType[];
}

interface EventType {
  name: string;
  token: string;
}
