import { Button } from '@/components/ui/button';
import { Loader2 } from 'lucide-react';
import { twMerge } from 'tailwind-merge';
import { useExportEvents } from '@/lib/teachingEvent/useExportEvents';
import { SelectedEventsState } from '@/components/teachingEvents/teaching-events';

interface ExportButtonProps {
  selectedEvents: SelectedEventsState;
  className?: string;
}

export default function ExportButton({
  selectedEvents,
  className,
}: Readonly<ExportButtonProps>) {
  const { isExporting, exportEvents } = useExportEvents();

  const selectedEventIds = Object.keys(selectedEvents).filter(
    (value) => selectedEvents[value],
  );

  function handleExport() {
    exportEvents(selectedEventIds);
  }

  return !isExporting ? (
    <Button className={twMerge('w-28', className)} onClick={handleExport}>
      Exportieren
    </Button>
  ) : (
    <Button disabled className={twMerge('w-28', className)}>
      <Loader2 className="mr-2 h-4 w-4 animate-spin" />
      Laden ...
    </Button>
  );
}
