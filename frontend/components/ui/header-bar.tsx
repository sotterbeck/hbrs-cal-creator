import { ReactNode } from 'react';

interface HeaderBarProps {
  title: string;
  description?: string;
  children?: ReactNode;
}

export default function HeaderBar({
  title,
  description,
  children,
}: HeaderBarProps) {
  return (
    <header className="sticky top-0 justify-between border-b bg-background/60 py-3 backdrop-blur">
      <div className="container flex h-12 flex-row items-center justify-between">
        <div>
          <h1 className=" text-xl font-medium">{title}</h1>
          <p className=" text-sm tabular-nums text-muted-foreground">
            {description}
          </p>
        </div>
        {children}
      </div>
    </header>
  );
}
