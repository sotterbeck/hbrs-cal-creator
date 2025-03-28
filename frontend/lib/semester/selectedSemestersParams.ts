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

// Dirty hack to fix the type error and make the search params compatible with Next.js 15.
// We need to update all usages of search params for the proper migration.
export async function resolveParams<T>(params: Promise<T>): Promise<T> {
  return await params;
}
