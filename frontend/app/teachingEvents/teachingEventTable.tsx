'use client';

import { DataTable } from '@/app/teachingEvents/dataTable';
import { columns } from '@/app/teachingEvents/tableColumns';

interface TeachingEventTableProps {
  teachingEvents: EventModel[];
}

export function TeachingEventTable({
  teachingEvents,
}: TeachingEventTableProps) {
  async function handleExport(ids: string[]) {
    console.log('Exporting teaching events with IDs:', ids);
    const params = new URLSearchParams();
    ids.forEach((id) => {
      params.append('event', id);
    });

    const response = await fetch(`/api/ical?${params.toString()}`);

    if (!response.ok) {
      console.error('Failed to export teaching events:', response.statusText);
      return;
    }

    const blob = await response.blob();
    const filename =
      response.headers.get('Content-Disposition')?.split('filename=')[1] ||
      'events.ics';
    const url = URL.createObjectURL(blob);

    const a = document.createElement('a');
    a.href = url;
    a.download = filename;
    document.body.appendChild(a);
    a.click();
    URL.revokeObjectURL(url);
  }

  return (
    <DataTable
      columns={columns}
      data={teachingEvents}
      onSubmit={handleExport}
    />
  );
}
