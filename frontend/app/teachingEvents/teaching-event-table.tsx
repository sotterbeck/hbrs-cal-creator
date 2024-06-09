'use client';

import { DataTable } from '@/app/teachingEvents/data-table';
import { columns } from '@/app/teachingEvents/table-columns';
import { useExportEvents } from '@/lib/useExportEvents';

interface TeachingEventTableProps {
  teachingEvents: EventModel[];
}

export function TeachingEventTable({
  teachingEvents,
}: TeachingEventTableProps) {
  const { isExporting, exportEvents } = useExportEvents();

  return (
    <DataTable
      columns={columns}
      data={teachingEvents}
      onExport={exportEvents}
      isExporting={isExporting}
    />
  );
}
