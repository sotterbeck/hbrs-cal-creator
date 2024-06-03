package de.sotterbeck.hbrscalcreator.reader

import assertk.all
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.extracting
import assertk.assertions.isNotEmpty
import de.sotterbeck.hbrscalcreator.downloader.TimeTableXlsSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.FileInputStream

class ApachePOITimeTableReaderTest {

    private val timeTable = "src/test/resources/bi1.xls"
    private val downloader = mockk<TimeTableXlsSource>()

    @BeforeEach
    fun setUp() {
        coEvery { downloader.fetchTimeTableXls(any(), any(), any()) } returns FileInputStream(timeTable)
    }

    @Test
    fun `should not return empty timetable when timetable has events`() {
        val reader = ApachePOITimeTableReader(downloader)

        val events = runBlocking { reader.readTimetable("any", "any", (15..18).toList()) }

        assertThat(events).isNotEmpty()
    }

    @Test
    fun `should return timetable with non-empty properties`() {
        val reader = ApachePOITimeTableReader(downloader)

        val events = runBlocking { reader.readTimetable("any", "any", (15..18).toList()) }

        assertThat(events).all {
            extracting { it["day"] }.isNotEmpty()
            extracting { it["startTime"] }.isNotEmpty()
            extracting { it["endTime"] }.isNotEmpty()
            extracting { it["room"] }.isNotEmpty()
            extracting { it["event"] }.isNotEmpty()
            extracting { it["period"] }.isNotEmpty()
            extracting { it["instructor"] }.isNotEmpty()
        }
    }

    @Test
    fun `should return timetable with valid days`() {
        val reader = ApachePOITimeTableReader(downloader)

        val events = runBlocking { reader.readTimetable("any", "any", (15..18).toList()) }

        assertThat(events)
            .extracting { it["day"] }
            .containsOnly("Mo", "Di", "Mi", "Do", "Fr")
    }

}