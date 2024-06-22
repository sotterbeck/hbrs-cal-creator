interface ItemSearchFn<T> {
  search(query: string | undefined, items: T[]): T[];
}
