import { DataTable } from '@/components/teachingEvents/data-table';
import { columns } from '@/components/teachingEvents/table-columns';
import { RowSelectionState } from '@tanstack/react-table';
import React from 'react';

interface TeachingEventTableProps {
  teachingEvents: EventModel[];
  rowSelection: RowSelectionState;
  setRowSelection: React.Dispatch<React.SetStateAction<RowSelectionState>>;
  className?: string;
}

export function TeachingEventTable({
  teachingEvents,
  rowSelection,
  setRowSelection,
  className,
}: Readonly<TeachingEventTableProps>) {
  return (
    <DataTable
      columns={columns}
      data={teachingEvents}
      rowSelection={rowSelection}
      setRowSelection={setRowSelection}
      className={className}
    />
  );
}
