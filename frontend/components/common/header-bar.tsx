'use client';
import { ReactNode } from 'react';
import { twMerge } from 'tailwind-merge';
import { useRouter } from 'next/navigation';
import { Button } from '@/components/ui/button';
import { ChevronLeft } from 'lucide-react';
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from '@/components/ui/tooltip';

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
  const router = useRouter();
  return (
    <header
      className={twMerge(
        'sticky top-0 justify-between border-b bg-background/60 py-3 backdrop-blur',
        className,
      )}
    >
      <div className="container flex h-12 flex-row items-center justify-between">
        <div className={'flex items-center gap-4'}>
          <TooltipProvider>
            <Tooltip>
              <TooltipTrigger asChild>
                <Button
                  variant={'ghost'}
                  size={'icon'}
                  onClick={() => router.back()}
                >
                  <ChevronLeft />
                </Button>
              </TooltipTrigger>
              <TooltipContent>
                <p>Zurück</p>
              </TooltipContent>
            </Tooltip>
          </TooltipProvider>
          <div>
            <h1 className="truncate font-medium leading-7 md:text-xl">
              {title}
            </h1>
            <p className="text-sm tabular-nums text-muted-foreground">
              {description}
            </p>
          </div>
        </div>
        {children}
      </div>
    </header>
  );
}
