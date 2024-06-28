package de.sotterbeck.hbrscalcreator.eva

import assertk.assertThat
import assertk.assertions.isNotEmpty
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Disabled("This test is disabled because it fetches data from the internet")
class WebEvaHtmlSourceIntegrationTest {

    @Autowired
    private lateinit var webEvaHtmlSource: WebEvaHtmlSource

    @Test
    fun `should return html from eva servers`() {
        val result = runBlocking { webEvaHtmlSource.downloadEvaSiteHtml() }

        assertThat(result).isNotEmpty()
    }
}