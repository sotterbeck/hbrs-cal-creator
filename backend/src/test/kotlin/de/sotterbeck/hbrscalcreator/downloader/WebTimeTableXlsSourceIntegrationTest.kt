package de.sotterbeck.hbrscalcreator.downloader

import de.sotterbeck.hbrscalcreator.eva.EvaInfoRepository
import kotlinx.coroutines.runBlocking
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WebTimeTableXlsSourceIntegrationTest {

    @Autowired
    private lateinit var webTimeTableXlsSource: WebTimeTableXlsSource

    @Autowired
    private lateinit var evaInfoRepository: EvaInfoRepository

    @Test
    fun `should fetch timetable that can be opened by ApachePOI`() {
        val termId = runBlocking { evaInfoRepository.findTermId() }
        val semesterId = "#SPLUS42C544"
        val weeks = runBlocking { evaInfoRepository.findAllWeeks() }

        val inputStream = runBlocking { webTimeTableXlsSource.fetchTimeTableXls(termId, semesterId, weeks) }

        assertDoesNotThrow {
            inputStream.use {
                val workbook = WorkbookFactory.create(it)
                workbook.close()
            }
        }
    }
}