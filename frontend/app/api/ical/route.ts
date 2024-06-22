import { NextRequest, NextResponse } from 'next/server';
import { getApiUrl } from '@/lib/api/data';

const API_URL = getApiUrl();

/**
 * Fetches iCal data for the selected events by proxying the request to the backend.
 * @param request
 */
export async function GET(request: NextRequest) {
  const urlParams = new URLSearchParams();
  const events = request.nextUrl.searchParams.getAll('event');

  events.forEach((event) => {
    urlParams.append('event', event);
  });

  const iCalRes = await fetch(
    `${API_URL}/api/teachingEvents/ical?${urlParams}`,
  );

  if (!iCalRes.ok) {
    return new NextResponse('Failed to fetch iCal data', {
      status: iCalRes.status,
    });
  }

  const iCalData = await iCalRes.text();

  return new NextResponse(iCalData, { headers: iCalRes.headers });
}
