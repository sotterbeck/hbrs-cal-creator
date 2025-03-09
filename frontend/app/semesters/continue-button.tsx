'use client';
import { Button } from '@/components/ui/button';
import Link from 'next/link';
import { twMerge } from 'tailwind-merge';
import React, { Suspense } from 'react';
import { useTransformedSearchParams } from '@/lib/common/useTransformedSearchParams';

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
  const params = useTransformedSearchParams([
    {
      attribute: 'semester',
      valueMapping: {
        Sprachkurse1: 'Sprachkurse',
        Wahlpflicht1: 'Wahlpflicht',
        Zusatzveranstaltungen1: 'Zusatzveranstaltungen',
      },
    },
  ]);

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
