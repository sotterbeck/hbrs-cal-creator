package de.sotterbeck.hbrscalcreator.downloader

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.sksamuel.aedile.core.cacheBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory

class WebTimeTableXlsSourceTest {

    private lateinit var webClient: WebClient
    private lateinit var server: MockWebServer
    private val cache = cacheBuilder<String, ByteArray>().build()

    private lateinit var uut: WebTimeTableXlsSource

    @BeforeEach
    fun setUp() {
        server = MockWebServer()
        server.start()
        server.dispatcher = MockEvaDispatcher

        val url = server.url("").toString()

        val uriBuilderFactory = DefaultUriBuilderFactory(url)
        uriBuilderFactory.encodingMode = DefaultUriBuilderFactory.EncodingMode.NONE

        webClient = WebClient.builder()
            .baseUrl(server.url("").toString())
            .uriBuilderFactory(uriBuilderFactory)
            .build()

        uut = WebTimeTableXlsSource(
            webClient = webClient,
            cache = cache
        )

    }

    @AfterEach
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `should call webclient with correct url`() {
        val termId = "1234"
        val semester = "BCS2"
        val weeks = (15..28).toList()

        runBlocking { uut.fetchTimeTableXls(termId, semester, weeks) }

        val request = server.takeRequest()
        assertThat(request.path).isEqualTo("/stundenplan/anzeigen/?weeks=15%3B16%3B17%3B18%3B19%3B20%3B21%3B22%3B23%3B24%3B25%3B26%3B27%3B28&days=1-7&mode=xls&identifier_semester=BCS2&show_semester=&identifier_dozent=&identifier_raum=&term=1234")
    }

    @Test
    fun `should return xls file`() {
        val termId = "1234"
        val semester = "BCS2"
        val weeks = (15..28).toList()

        val xls = runBlocking { uut.fetchTimeTableXls(termId, semester, weeks) }

        assertDoesNotThrow {
            WorkbookFactory.create(xls)
        }
    }
}