import { ReactNode } from 'react';
import { twMerge } from 'tailwind-merge';

interface HeaderBarProps {
  title: string;
  description?: string;
  children?: ReactNode;
  className?: string;
}

export default function HeaderBar({
  title,
  description,
  children,
  className,
}: Readonly<HeaderBarProps>) {
  return (
    <header
      className={twMerge(
        'sticky top-0 justify-between border-b bg-background/60 py-3 backdrop-blur',
        className,
      )}
    >
      <div className="container flex h-12 flex-row items-center justify-between">
        <div>
          <h1 className="truncate font-medium leading-7 md:text-xl">{title}</h1>
          <p className="text-sm tabular-nums text-muted-foreground">
            {description}
          </p>
        </div>
        {children}
      </div>
    </header>
  );
}
