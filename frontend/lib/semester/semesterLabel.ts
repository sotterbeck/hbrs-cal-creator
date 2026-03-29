/**
 * Normalizes a semester name by removing all spaces.
 * @param semesterName The semester name to normalize.
 */
export function normalizeSemesterToken(semesterName: string): string {
  return semesterName.replaceAll(' ', '');
}

/**
 * Parses a semester label into a display label and optional PO year.
 * @param semesterLabel The semester label to parse.
 */
export function parseSemesterLabel(semesterLabel: string): {
  displayLabel: string;
  poYear?: string;
} {
  const match = semesterLabel.match(/^(\d+)\s*(?:\(PO\s*(\d{4})\))?/);
  if (match) {
    return {
      displayLabel: match[1],
      poYear: match[2] ?? undefined,
    };
  }

  return { displayLabel: semesterLabel };
}
