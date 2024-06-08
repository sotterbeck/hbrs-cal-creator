import { Loader2 } from 'lucide-react';
import { ReactNode } from 'react';

interface SpinnerProps {
  text: ReactNode;
}

const defaultProps: SpinnerProps = {
  text: 'Loading...',
};

export function Spinner(props: SpinnerProps) {
  return (
    <div className="flex items-center gap-2">
      <Loader2 className="h-4 w-4 animate-spin" />
      <span>{props.text}</span>
    </div>
  );
}

Spinner.defaultProps = defaultProps;
