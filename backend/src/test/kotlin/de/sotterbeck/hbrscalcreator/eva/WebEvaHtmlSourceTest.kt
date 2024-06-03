package de.sotterbeck.hbrscalcreator.eva

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.sksamuel.aedile.core.cacheBuilder
import de.sotterbeck.hbrscalcreator.downloader.MockEvaDispatcher
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient

class WebEvaHtmlSourceTest {

    private lateinit var webClient: WebClient
    private lateinit var server: MockWebServer
    private val cache = cacheBuilder<String, String>().build()

    private lateinit var uut: WebEvaHtmlSource

    @BeforeEach
    fun setUp() {
        server = MockWebServer()
        server.start()
        server.dispatcher = MockEvaDispatcher

        val url = server.url("").toString()

        webClient = WebClient.builder()
            .baseUrl(server.url("").toString())
            .build()

    }

    @Test
    fun `should return html`() {
        uut = WebEvaHtmlSource(webClient, cache)

        val result = runBlocking { uut.downloadEvaSiteHtml() }

        assertThat(result).isEqualTo(MockEvaDispatcher.mainPageHtml)
    }
}