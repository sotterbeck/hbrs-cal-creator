import HeaderBar from '@/components/common/header-bar';
import { ModeToggle } from '@/components/ui/mode-toggle';
import ExportButton from '@/components/teachingEvents/export-button';
import { SelectedEventsState } from '@/components/teachingEvents/teaching-events';

interface TeachingEventsHeaderProps {
  selectedEvents: SelectedEventsState;
}

export default function TeachingEventsHeader({
  selectedEvents,
}: Readonly<TeachingEventsHeaderProps>) {

  let numberOfSelectedEvents =
    Object.values(selectedEvents).filter(Boolean).length;

  return (
    <div className="sticky top-0 z-10 backdrop-blur">
      <HeaderBar
        title={'Wähle deine Veranstaltungen'}
        description={`${numberOfSelectedEvents} Veranstaltungen ausgewählt`}
        className="backdrop-blur-none"
      >
        <div className="flex gap-2">
          <ExportButton
            selectedEvents={selectedEvents}
            className="hidden md:inline-flex"
          />
          <ModeToggle />
        </div>
      </HeaderBar>
    </div>
  );
}
