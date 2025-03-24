import { Button } from '@/components/ui/button';
import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from '@/components/ui/accordion';
import Image from 'next/image';
import Link from 'next/link';
import Footer from '@/app/footer';

export default function Home() {
  return (
    <main>
      <div className="flex flex-col gap-8 bg-cyan-950 px-6 pb-8 pt-24">
        <div className="flex flex-col gap-6">
          <h1 className="text-center text-4xl font-bold text-white md:text-6xl">
            H-BRS Cal Creator
          </h1>
          <p className=" text-center text-cyan-200">
            Exportiere deine Veranstaltungen an der H-BRS in deinen Kalender
          </p>
        </div>
        <div className="flex justify-center gap-2">
          <Button asChild>
            <Link href="/semesters">Los geht&rsquo;s</Link>
          </Button>
          <Button variant="link" className="text-white" asChild>
            <Link target={"_blank"} href="https://github.com/sotterbeck/hbrs-cal-creator">
              GitHub
            </Link>
          </Button>
        </div>
        <div className="relative mx-auto">
          <Image
            src="/app-overview.svg"
            width={626}
            height={366}
            alt="An simplified view of the teaching event selection page"
            className="rounded-lg"
          />
          <div className="absolute inset-0 bg-gradient-to-t from-cyan-950"></div>
        </div>
      </div>
      <section className="container py-12">
        <h2 className="mb-6 text-2xl font-bold md:mb-8 md:text-4xl">Über</h2>
        <p className="mb-4">
          Ein Tool, um Veranstaltungen der Hochschule Bonn-Rhein-Sieg in deinen
          Kalender zu importieren.
        </p>
        <p className="mb-6">
          Statt mühsam jede Veranstaltung einzeln in deinen Kalender
          einzutragen, kannst du hier einfach deinen Studiengang und die
          gewünschten Veranstaltungen auswählen und erhältst eine iCal-Datei,
          die du in deinen Kalender importieren kannst.
        </p>
        <p className="text-sm text-muted-foreground">
          Dieses Projekt ist nicht offiziell von der Hochschule Bonn-Rhein-Sieg
          und wird nicht von ihr unterstützt.
        </p>
      </section>
      <div className="container">
        <hr className="border-2" />
      </div>
      <section className="container space-y-2 py-8">
        <h2 className="mb-6 text-2xl font-bold md:mb-8 md:text-4xl">
          Häufig gestellte Fragen
        </h2>

        <Accordion type="single" collapsible>
          <AccordionItem value="item-1">
            <AccordionTrigger>
              Wie benutze ich den Cal Creator?
            </AccordionTrigger>
            <AccordionContent>
              Wähle deinen Studiengang aus und wähle die gewünschten
              Veranstaltungen aus. Wenn du fertig bist, erhältst du eine
              iCal-Datei, die du in deinen Kalender importieren kannst.
            </AccordionContent>
          </AccordionItem>
          <AccordionItem value="item-2">
            <AccordionTrigger>
              Was mache ich mit der iCal-Datei?
            </AccordionTrigger>
            <AccordionContent>
              <p className="mb-6">
                Du kannst die iCal-Datei (.ics) in deinem Kalender importieren.
                Wie das genau funktioniert, hängt von deinem Kalender ab.
              </p>
              <p className="font-medium">
                Hier sind Anleitungen für die gängigsten Kalenderprogramme
              </p>
              <ul className="my-4 ml-6 list-disc [&>li]:mt-2">
                <li>
                  <a
                    className="text-blue-600 underline"
                    href="https://support.google.com/calendar/answer/37118?hl=de"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    Google Kalender
                  </a>
                </li>
                <li>
                  <a
                    className="text-blue-600 underline"
                    href="https://support.microsoft.com/de-de/office/importieren-oder-abonnieren-eines-kalenders-in-outlook-com-oder-outlook-im-web-cff1429c-5af6-41ec-a5b4-74f2c278e98c"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    Outlook
                  </a>
                </li>
                <li>
                  <a
                    className="text-blue-600 underline"
                    href="https://support.apple.com/de-de/guide/calendar/icl1023/mac"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    Apple Kalender
                  </a>
                </li>
              </ul>
            </AccordionContent>
          </AccordionItem>
          <AccordionItem value="item-3">
            <AccordionTrigger>
              Wie aktualisiere ich meinen Kalender?
            </AccordionTrigger>
            <AccordionContent>
              <p className="mb-2">
                Wenn sich dein Stundenplan ändert, musst du die iCal-Datei
                erneut herunterladen und in deinen Kalender importieren.
              </p>
              <p>
                Damit das Löschen von alten Veranstaltungen schneller geht,
                empfiehlt es sich einen eigenen Kalender für die importierten
                Veranstaltungen zu erstellen.
              </p>
            </AccordionContent>
          </AccordionItem>
        </Accordion>
      </section>
      <Footer />
    </main>
  );
}
