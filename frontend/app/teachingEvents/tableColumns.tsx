'use client';
import { ColumnDef } from '@tanstack/react-table';
import { TeachingEvent, TeachingEventType } from '@/lib/data';
import { Badge } from '@/components/ui/badge';
import { Checkbox } from '@/components/ui/checkbox';

export const columns: ColumnDef<TeachingEvent>[] = [
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
          {truncate(title, 50)}
        </div>
      );
    },
  },
  {
    accessorKey: 'time',
    header: () => <div className="text-right">Time</div>,
    cell: ({ row }) => {
      return <div className="text-right">{row.getValue('time')}</div>;
    },
  },
  {
    accessorKey: 'room',
    header: 'Room',
  },
  {
    accessorKey: 'lecturer',
    header: 'Lecturer',
  },
  {
    accessorKey: 'types',
    header: 'Types',
    cell: ({ row }) => {
      const types = row.getValue('types') as string[];

      return (
        <div className="flex gap-1">
          {types.map((type) => (
            <Badge key={type} variant="secondary">
              {type.charAt(0)}
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
  {
    accessorKey: 'info',
    header: 'Info',
  },
];

// truncate words function with ellipsis at if it was cut off
function truncateWords(str: string, wordNumber: number) {
  const words = str.split(' ');

  if (words.length > wordNumber) {
    return words.slice(0, wordNumber).join(' ') + '...';
  }
  return str;
}

function truncate(str: string, length: number) {
  return str.length > length ? str.slice(0, length) + '...' : str;
}
