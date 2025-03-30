import { Checkbox } from '@/components/ui/checkbox';
import { Badge } from '@/components/ui/badge';
import { cn } from '@/lib/utils';

interface TeachingEventCardProps {
  id: string;
  title: string;
  instructor: string;
  time: string;
  types: string[];
  group: string | null;
  selected: boolean;
  setSelected: (value: boolean, id: string) => void;
  className?: string;
}

export default function TeachingEventCard({
  id,
  title,
  instructor,
  time,
  types,
  group,
  selected,
  setSelected,
  className,
}: Readonly<TeachingEventCardProps>) {
  return (
    <div
      className={cn('rounded-lg border bg-card p-4 shadow-sm', className, {
        'border-primary': selected,
      })}
    >
      <div className="items-top flex space-x-2">
        <Checkbox
          id={id}
          checked={selected}
          onCheckedChange={(checked) => {
            return setSelected(checked as boolean, id);
          }}
        />
        <div className="grid gap-1 leading-none">
          <label
            htmlFor={id}
            className="pt-px text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
          >
            {title}
          </label>
          <div>
            <p className="text-sm text-muted-foreground">{instructor}</p>
            <p className="text-sm tabular-nums text-muted-foreground">{time}</p>
          </div>
          <div className="flex gap-1 pt-2">
            {types.map((type) => (
              <Badge key={type} variant="outline">
                {type}
              </Badge>
            ))}
            {group && <Badge variant="secondary">{`Gr. ${group}`}</Badge>}
          </div>
        </div>
      </div>
    </div>
  );
}
