package de.sotterbeck.hbrscalcreator.eva

import com.sksamuel.aedile.core.Cache
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class WebEvaHtmlSource(
    private val webClient: WebClient,
    @Qualifier("evaHtmlCache") private val cache: Cache<String, String>
) : EvaHtmlSource {

    private val logger = LoggerFactory.getLogger(WebEvaHtmlSource::class.java)

    override suspend fun downloadEvaSiteHtml(): String {
        return cache.get("evaHtml") {
            fetchHtml()
        }
    }

    private suspend fun fetchHtml(): String {
        val html = webClient.get()
            .uri("/stundenplan/")
            .accept(MediaType.TEXT_HTML)
            .retrieve()
            .awaitBody<String>()

        logger.info("Fetched eva2 HTML from web.")
        return html
    }

}