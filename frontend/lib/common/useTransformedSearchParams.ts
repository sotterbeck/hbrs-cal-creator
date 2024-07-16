import { useSearchParams } from 'next/navigation';

interface AttributeTransformer {
  attribute: string;
  valueMapping: Record<string, string>;
}

export function useTransformedSearchParams(
  transformations: AttributeTransformer[],
): URLSearchParams {
  const readOnlyParams = useSearchParams();

  const params = new URLSearchParams(readOnlyParams);

  transformations.forEach((transformations) => {
    params.getAll(transformations.attribute).map((param) => {
      const transformedParam = transformations.valueMapping[param];
      if (transformedParam) {
        params.delete(transformations.attribute, param);
        params.append(transformations.attribute, transformedParam);
      }
    });
  });
  return params;
}
