export type SelectedSemestersParams = {
  semester?: string[] | string;
};

export function getSelectedSemestersCount(
  searchParams: SelectedSemestersParams,
) {
  if (!searchParams.semester) {
    return 0;
  }
  return Array.isArray(searchParams.semester)
    ? searchParams.semester.length
    : 1;
}

export function getSelectedSemesters(
  searchParams: SelectedSemestersParams | URLSearchParams,
): string[] {
  if (searchParams instanceof URLSearchParams) {
    return searchParams.getAll('semester');
  }

  if (!searchParams.semester) {
    return [];
  }

  return Array.isArray(searchParams.semester)
    ? searchParams.semester
    : [searchParams.semester];
}

export function removeSemesterFromParams(
  searchParams: URLSearchParams,
  semester: string,
) {
  const params = new URLSearchParams(searchParams);
  params.delete('semester', semester);

  return params;
}

export function addSemesterToParams(
  searchParams: URLSearchParams,
  semester: string,
) {
  const params = new URLSearchParams(searchParams);

  params.append('semester', semester);

  return params;
}
