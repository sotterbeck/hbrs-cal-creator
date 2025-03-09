const hiddenSemesters = ['Wahlpflicht'];

export function isHiddenSemester(semester: string): boolean {
  return hiddenSemesters.includes(semester);
}
