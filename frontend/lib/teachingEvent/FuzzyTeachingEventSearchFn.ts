import Fuse, {IFuseOptions} from 'fuse.js';

/**
 * Item search function for fuzzy search of teaching events.
 */
export class FuzzyTeachingEventSearchFn implements ItemSearchFn<EventModel> {
  private options: IFuseOptions<EventModel> = {
    keys: ['module', 'instructor', 'day', 'room', 'group'],
    useExtendedSearch: true,
    threshold: 0.3,
  };

  search(query: string, items: EventModel[]): EventModel[] {
    if (!query) {
      return items;
    }

    const fuse = new Fuse(items, this.options);
    return fuse.search(query).map((result) => result.item);
  }
}
