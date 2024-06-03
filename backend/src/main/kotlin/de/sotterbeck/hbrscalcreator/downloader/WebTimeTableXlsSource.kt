package de.sotterbeck.hbrscalcreator.downloader

import com.sksamuel.aedile.core.Cache
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.net.URLEncoder

@Component
class WebTimeTableXlsSource(
    private val webClient: WebClient,
    @Qualifier("timeTableCache") private val cache: Cache<String, ByteArray>
) : TimeTableXlsSource {

    private val logger = LoggerFactory.getLogger(WebTimeTableXlsSource::class.java)

    override suspend fun fetchTimeTableXls(termId: String, semesterId: String, weeks: List<Int>): InputStream {
        val weeksParam = weeks.joinToString(separator = ";")

        val response = cache.get("$termId-$semesterId-$weeksParam") {
            fetchTimeTable(weeksParam, semesterId, termId, weeks)
        }

        return ByteArrayInputStream(response)
    }

    private suspend fun fetchTimeTable(
        weeksParam: String,
        semesterId: String,
        termId: String,
        weeks: List<Int>
    ): ByteArray {
        val response = webClient.get()
            .uri {
                it.path("/stundenplan/anzeigen/")
                    .queryParam("weeks", URLEncoder.encode(weeksParam, "UTF-8"))
                    .queryParam("days", "1-7")
                    .queryParam("mode", "xls")
                    .queryParam("identifier_semester", URLEncoder.encode(semesterId, "UTF-8"))
                    .queryParam("show_semester", "")
                    .queryParam("identifier_dozent", "")
                    .queryParam("identifier_raum", "")
                    .queryParam("term", termId)
                    .build()
            }
            .accept(MediaType.APPLICATION_OCTET_STREAM)
            .retrieve()
            .awaitBody<ByteArray>()

        logger.info("Fetched $semesterId timetable XLS for weeks ${weeks.min()}-${weeks.max()} from web.")
        return response
    }
}