import {
  ReadonlyURLSearchParams,
  usePathname,
  useRouter,
  useSearchParams,
} from 'next/navigation';
import { NavigateOptions } from 'next/dist/shared/lib/app-router-context.shared-runtime';

export function useSearchParamsManipulation(): {
  readonlyURLSearchParams: ReadonlyURLSearchParams;
  replace: (href: string, options?: NavigateOptions) => void;
  pathname: string;
} {
  const readonlyURLSearchParams = useSearchParams();
  const pathname = usePathname();
  const { replace } = useRouter();
  return { readonlyURLSearchParams, pathname, replace };
}
