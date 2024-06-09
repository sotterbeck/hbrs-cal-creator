import { useState } from 'react';
import { useToast } from '@/components/ui/use-toast';

export function useExportEvents() {
  const [isExporting, setIsExporting] = useState(false);
  const { toast } = useToast();

  async function exportEvents(ids: string[]) {
    if (!ids.length) {
      toast({
        title: 'Keine Veranstaltungen ausgewählt',
      });
      return;
    }
    setIsExporting(true);
    const params = new URLSearchParams();

    ids.forEach((id) => {
      params.append('event', id);
    });

    const response = await fetch(`/api/ical?${params.toString()}`);

    if (!response.ok) {
      toast({
        title: 'Exportieren fehlgeschlagen',
        description:
          'Die iCal-Daten konnten nicht exportiert werden. Bitte versuche später es erneut.',
        variant: 'destructive',
      });
      setIsExporting(false);
      return;
    }

    const blob = await response.blob();
    const filename =
      response.headers.get('Content-Disposition')?.split('filename=')[1] ||
      'events.ics';
    const url = URL.createObjectURL(blob);

    sendFileToClient(url, filename);

    setIsExporting(false);
  }

  return { isExporting, exportEvents };
}

function sendFileToClient(url: string, filename: string) {
  const a = document.createElement('a');
  a.href = url;
  a.download = filename;
  document.body.appendChild(a);
  a.click();
  URL.revokeObjectURL(url);
}
