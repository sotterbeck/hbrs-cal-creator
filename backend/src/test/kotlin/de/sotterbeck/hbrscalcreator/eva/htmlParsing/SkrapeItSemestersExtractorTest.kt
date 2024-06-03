package de.sotterbeck.hbrscalcreator.eva.htmlParsing

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import de.sotterbeck.hbrscalcreator.eva.SemesterDto
import de.sotterbeck.hbrscalcreator.eva.htmlParsing.skrapeIt.SkrapeItSemestersExtractor
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class SkrapeItSemestersExtractorTest {

    private val html = """
        <!DOCTYPE html>
        <html lang="de">
          <head><title>Eva</title></head>
          <body>
            <div class="container">
              <h3>Stundenplan</h3>
              <form action="anzeigen/" method="GET" role="form">
                <h4>Stundenplan-Konfiguration:</h4>
                <div class="form-group config">
                  <label for="input_weeks">Wochen</label>
                  <select
                    class="custom-select form-control"
                    name="weeks"
                    id="input_weeks"
                  >
                    <option value="15;16;17;18;19;20;21;22;23;24;25;26;27;28" selected>
                      gesamtes Semester (KW 15-28)
                    </option>
                    <option value="16;18;20;22;24;26;28">
                      gerade Kalenderwochen (KW 16-28)
                    </option>
                    <option value="15;17;19;21;23;25;27">
                      ungerade Kalenderwochen (KW 15-27)
                    </option>
                    <option value="15">KW 15</option>
                    <option value="16">KW 16</option>
                    <option value="17">KW 17</option>
                    <option value="18">KW 18</option>
                    <option value="19">KW 19</option>
                    <option value="20">KW 20</option>
                    <option value="21">KW 21 (aktuelle Woche)</option>
                    <option value="22">KW 22</option>
                    <option value="23">KW 23</option>
                    <option value="24">KW 24</option>
                    <option value="25">KW 25</option>
                    <option value="26">KW 26</option>
                    <option value="27">KW 27</option>
                    <option value="28">KW 28</option>
                  </select>
                </div>
                <div class="selectbox">
                  <div class="form-group">
                    <label for="identifier_semester"
                      >Studiengang / Semester <sup>*</sup></label
                    >
                    <select
                      class="custom-select form-control"
                      id="identifier_semester"
                      name="identifier_semester"
                    >
                      <option value="">*** bitte auswählen ***</option>
                      <option value="#SPLUS42C544">BCSP 1</option>
                      <option value="#SPLUSD4951B">BCSP 2</option>
                      <option value="#SPLUS42C545">BCSP 3</option>
                      <option value="#SPLUS4BB425">BCSP 4</option>
                      <option value="#SPLUS42C53D">BI 1</option>
                      <option value="BCS2">BI 2</option>
                      <option value="#SPLUS42C53E">BI 3</option>
                      <option value="BCS4">BI 4</option>
                      <option value="#SPLUS42C53F">BI 5</option>
                      <option value="#SPLUS42C541">BWI 1</option>
                      <option value="BBIS2">BWI 2</option>
                      <option value="BBIS4">BWI 4</option>
                      <option value="#SPLUS42C543">BWI 5</option>
                      <option value="MAS1">MAS 1</option>
                      <option value="MAS2">MAS 2</option>
                      <option value="MAS3">MAS 3</option>
                      <option value="#SPLUSE4D31E">MAS 4</option>
                      <option value="#SPLUS5E854A">MCSP 1</option>
                      <option value="#SPLUSECE0E1">MCSP 2</option>
                      <option value="MCS1">MI 1</option>
                      <option value="MCS2">MI 2</option>
                      <option value="MCS3">MI 3</option>
                      <option value="#SPLUS4222DA">MKSN</option>
                      <option value="#SPLUS157A20">MVG 1</option>
                      <option value="#SPLUSAC7DD0">MVG 2</option>
                      <option value="#SPLUSAC7DD1">MVG 3</option>
                      <option value="#SPLUS382187">Zusatzveranstaltungen</option>
                    </select>
                  </div>
                </div>
                <input
                  type="hidden"
                  name="term"
                  value="f685ba7c997816b8a0a640622adc862c"
                />
              </form>
              <div>
              </div>
            </div>
          </body>
        </html>
    """.trimIndent()

    private val uut = EvaExtractor.extractors().createSemestersExtractor()

    @Test
    fun `should extract semesters when they are present`() {
        val semesters = runBlocking { uut.extractSemesters(html) }

        assertThat(semesters).isEqualTo(
            listOf(
                SemesterDto("BCSP 1", "#SPLUS42C544"),
                SemesterDto("BCSP 2", "#SPLUSD4951B"),
                SemesterDto("BCSP 3", "#SPLUS42C545"),
                SemesterDto("BCSP 4", "#SPLUS4BB425"),
                SemesterDto("BI 1", "#SPLUS42C53D"),
                SemesterDto("BI 2", "BCS2"),
                SemesterDto("BI 3", "#SPLUS42C53E"),
                SemesterDto("BI 4", "BCS4"),
                SemesterDto("BI 5", "#SPLUS42C53F"),
                SemesterDto("BWI 1", "#SPLUS42C541"),
                SemesterDto("BWI 2", "BBIS2"),
                SemesterDto("BWI 4", "BBIS4"),
                SemesterDto("BWI 5", "#SPLUS42C543"),
                SemesterDto("MAS 1", "MAS1"),
                SemesterDto("MAS 2", "MAS2"),
                SemesterDto("MAS 3", "MAS3"),
                SemesterDto("MAS 4", "#SPLUSE4D31E"),
                SemesterDto("MCSP 1", "#SPLUS5E854A"),
                SemesterDto("MCSP 2", "#SPLUSECE0E1"),
                SemesterDto("MI 1", "MCS1"),
                SemesterDto("MI 2", "MCS2"),
                SemesterDto("MI 3", "MCS3"),
                SemesterDto("MKSN", "#SPLUS4222DA"),
                SemesterDto("MVG 1", "#SPLUS157A20"),
                SemesterDto("MVG 2", "#SPLUSAC7DD0"),
                SemesterDto("MVG 3", "#SPLUSAC7DD1"),
                SemesterDto("Zusatzveranstaltungen", "#SPLUS382187")
            )
        )
    }

    @Test
    fun `should thow error when semesters are not present`() {
        val uut = SkrapeItSemestersExtractor()

        assertFailure {
            runBlocking { uut.extractSemesters("") }
        }.isInstanceOf<IllegalStateException>()
    }

}