import { Card, CardContent, CardHeader } from '@/components/ui/card';
import React from 'react';
import { Skeleton } from '@/components/ui/skeleton';

export default function SemesterCardSkeleton() {
  return (
    <Card>
      <CardHeader className="h-32 overflow-hidden rounded-t-lg bg-zinc-50 text-center dark:bg-accent">
        <Skeleton className="mx-auto h-6 w-12" />
        <Skeleton className="mx-auto h-5 w-48" />
      </CardHeader>
      <CardContent className="space-y-1 pt-4">
        <Skeleton className="h-6 w-24" />
        <Skeleton className="h-10 w-32" />
      </CardContent>
    </Card>
  );
}
