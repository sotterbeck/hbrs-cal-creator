import { FuzzyTeachingEventSearchFn } from '@/lib/teachingEvent/FuzzyTeachingEventSearchFn';

export function useTeachingEventSearching(): ItemSearchFn<EventModel> {
  return new FuzzyTeachingEventSearchFn();
}
