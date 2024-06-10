import { Spinner } from '@/components/ui/spinner';

export default function Page() {
  return (
    <div className="flex h-screen justify-center align-middle ">
      <Spinner text="Lade aktuelle Veranstaltungsdaten" />
    </div>
  );
}
