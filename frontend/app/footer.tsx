import { Button } from '@/components/ui/button';
import Link from 'next/link';
import Image from 'next/image';

export default function Footer() {
  return (
    <footer className="bg-accent">
      <div className="container flex items-center justify-between px-6 py-2">
        <Link
          className="text-sm text-muted-foreground hover:underline"
          href="/privacy"
        >
          Datenschutzerklärung
        </Link>
        <Button
          variant="link"
          className="text-muted-foreground"
          size="icon"
          asChild
        >
          <Link
            href="https://github.com/sotterbeck/hbrs-cal-creator"
            target="_blank"
            rel="noopener noreferrer"
          >
            <Image
              src="/github.svg"
              alt="GitHub Logo"
              width={24}
              height={24}
              className="fill-red-50"
            />
          </Link>
        </Button>
      </div>
    </footer>
  );
}
