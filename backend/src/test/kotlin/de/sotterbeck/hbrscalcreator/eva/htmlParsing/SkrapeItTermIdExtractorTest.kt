package de.sotterbeck.hbrscalcreator.eva.htmlParsing

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import de.sotterbeck.hbrscalcreator.eva.htmlParsing.skrapeIt.SkrapeItTermIdExtractor
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class SkrapeItTermIdExtractorTest {

    val html = """
        <!DOCTYPE html>
        <html lang="de">
          <head><title>Time Table</title></head>
          <body>
            <div>
              <form action="anzeigen/" method="GET" role="form">
                <input
                  type="hidden"
                  name="term"
                  value="f685ba7c997816b8a0a640622adc862c"
                />
              </form>
            </div>
          </body>
        </html>
    """.trimIndent()

    val uut = EvaExtractor.extractors().createTermIdExtractor()

    @Test
    fun `should extract term token when its present`() {
        val termToken = runBlocking { uut.extractTermId(html) }

        assertThat(termToken).isEqualTo("f685ba7c997816b8a0a640622adc862c")
    }

    @Test
    fun `should throw error when term token is not present`() {
        val uut = SkrapeItTermIdExtractor()

        assertFailure {
            runBlocking { uut.extractTermId("") }
        }.isInstanceOf<IllegalStateException>()
    }
}