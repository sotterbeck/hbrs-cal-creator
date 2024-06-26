'use client';
import { Button } from '@/components/ui/button';
import Link from 'next/link';
import { useSearchParams } from 'next/navigation';
import { twMerge } from 'tailwind-merge';
import React, { Suspense } from 'react';

export interface ContinueButtonProps {
  className?: string;
}

export default function ContinueButton({
  className,
}: Readonly<ContinueButtonProps>) {
  return (
    <Suspense>
      <ContinueButtonWithParams className={className} />
    </Suspense>
  );
}

function ContinueButtonWithParams({
  className,
}: Readonly<ContinueButtonProps>) {
  const params = useSearchParams();

  return (
    <Button
      variant="default"
      className={twMerge(
        'w-full shadow-lg md:w-auto md:shadow-none',
        className,
      )}
      asChild
    >
      <Link
        href={{
          pathname: '/teachingEvents',
          query: params.toString(),
        }}
      >
        Weiter
      </Link>
    </Button>
  );
}
