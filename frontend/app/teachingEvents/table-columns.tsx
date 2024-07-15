'use client';
import { ColumnDef } from '@tanstack/react-table';
import { Badge } from '@/components/ui/badge';
import { Checkbox } from '@/components/ui/checkbox';

export const columns: ColumnDef<EventModel>[] = [
  {
    id: 'select',
    header: ({ table }) => (
      <Checkbox
        className="translate-y-px"
        checked={
          table.getIsAllPageRowsSelected() ||
          (table.getIsSomePageRowsSelected() && 'indeterminate')
        }
        onCheckedChange={(value) => table.toggleAllPageRowsSelected(!!value)}
        aria-label="Select all"
      />
    ),
    cell: ({ row }) => (
      <Checkbox
        className="translate-y-0.5"
        checked={row.getIsSelected()}
        onCheckedChange={(value) => row.toggleSelected(!!value)}
        aria-label="Select row"
      />
    ),
  },
  {
    accessorKey: 'title',
    header: 'Title',
    cell: ({ row }) => {
      const title = row.getValue('title') as string;

      return (
        <div className="font-medium" title={title}>
          {title}
        </div>
      );
    },
  },
  {
    header: 'Time',
    accessorFn: (row) => `${row.day}. ${row.startTime}-${row.endTime}`,
  },
  {
    accessorKey: 'room',
    header: 'Room',
  },
  {
    accessorKey: 'instructor',
    header: 'Instructor',
  },
  {
    accessorKey: 'types',
    header: 'Types',
    cell: ({ row }) => {
      const types = row.getValue('types') as EventType[];

      return (
        <div className="flex gap-1">
          {types.map((type) => (
            <Badge key={type.token} variant="secondary">
              {type.token}
            </Badge>
          ))}
        </div>
      );
    },
  },
  {
    accessorKey: 'group',
    header: 'Group',
  },
];
