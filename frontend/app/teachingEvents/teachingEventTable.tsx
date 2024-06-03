'use client';

import { TeachingEvent } from '@/lib/data';
import { DataTable } from '@/app/teachingEvents/dataTable';
import { columns } from '@/app/teachingEvents/tableColumns';

interface TeachingEventTableProps {
  teachingEvents: TeachingEvent[];
}

export function TeachingEventTable({
  teachingEvents,
}: TeachingEventTableProps) {
  function handleExport(ids: string[]) {
    console.log('Exporting teaching events with IDs:', ids);
  }

  return (
    <DataTable
      columns={columns}
      data={teachingEvents}
      onSubmit={handleExport}
    />
  );
}
