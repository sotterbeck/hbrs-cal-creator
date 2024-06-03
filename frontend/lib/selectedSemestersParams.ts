export type SelectedSemestersParams = {
  semester?: string[];
};

export function getSelectedSemestersCount(
  searchParams: SelectedSemestersParams,
) {
  if (!searchParams.semester) {
    return 0;
  }

  return searchParams.semester.length;
}

export function getSelectedSemesters(
  searchParams: SelectedSemestersParams | URLSearchParams,
) {
  if (searchParams instanceof URLSearchParams) {
    return searchParams.getAll('semester');
  }
  return searchParams.semester || [];
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
