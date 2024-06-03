package de.sotterbeck.hbrscalcreator.eva

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import de.sotterbeck.hbrscalcreator.downloader.MockEvaDispatcher
import de.sotterbeck.hbrscalcreator.eva.htmlParsing.EvaExtractorFactory
import de.sotterbeck.hbrscalcreator.eva.htmlParsing.SemestersExtractor
import de.sotterbeck.hbrscalcreator.eva.htmlParsing.TermIdExtractor
import de.sotterbeck.hbrscalcreator.eva.htmlParsing.WeeksExtractor
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EvaInfoRepositoryImplTest {

    private val evaHtmlSource = mockk<EvaHtmlSource>()
    private val extractorFactory = mockk<EvaExtractorFactory>()

    private val termIdExtractor = mockk<TermIdExtractor>()
    private val weeksExtractor = mockk<WeeksExtractor>()
    private val semestersExtractor = mockk<SemestersExtractor>()

    private lateinit var uut: EvaInfoRepositoryImpl

    @BeforeEach
    fun setUp() {
        coEvery { evaHtmlSource.downloadEvaSiteHtml() } returns MockEvaDispatcher.mainPageHtml
        every { extractorFactory.createTermIdExtractor() } returns termIdExtractor
        every { extractorFactory.createWeeksExtractor() } returns weeksExtractor
        every { extractorFactory.createSemestersExtractor() } returns semestersExtractor

        uut = EvaInfoRepositoryImpl(
            extractorFactory = extractorFactory,
            evaHtmlSource = evaHtmlSource
        )
    }

    @Test
    fun `should find term id`() {
        val termId = "1234"
        coEvery { termIdExtractor.extractTermId(MockEvaDispatcher.mainPageHtml) } returns termId

        val result = runBlocking { uut.findTermId() }

        assertThat(result).isEqualTo(termId)
    }

    @Test
    fun `should find all weeks`() {
        val weeks = listOf(1, 2, 3, 4, 5)
        coEvery { weeksExtractor.extractWeeks(MockEvaDispatcher.mainPageHtml) } returns weeks

        val result = runBlocking { uut.findAllWeeks() }

        assertThat(result).isEqualTo(weeks)
    }

    @Test
    fun `should find all semesters`() {
        val semesters = listOf(
            SemesterDto("BCSP 1", "#SPLUS42C544"),
            SemesterDto("BCSP 2", "#SPLUSD4951B"),
            SemesterDto("BCSP 3", "#SPLUS42C545"),
            SemesterDto("BCSP 4", "#SPLUS4BB425")
        )
        coEvery { semestersExtractor.extractSemesters(MockEvaDispatcher.mainPageHtml) } returns semesters

        val result = runBlocking { uut.findAllSemesters() }

        assertThat(result).isEqualTo(semesters)
    }

    @Test
    fun `should find semester by id`() {
        val semester = SemesterDto("BCSP 1", "#SPLUS42C544")
        coEvery { semestersExtractor.extractSemesters(MockEvaDispatcher.mainPageHtml) } returns listOf(semester)

        val result = runBlocking { uut.findSemesterById(semester.evaId) }

        assertThat(result).isEqualTo(semester)
    }

    @Test
    fun `should find eva id by token when semester exists`() {
        val semesters = listOf(
            SemesterDto("BCSP 1", "#SPLUS42C544"),
            SemesterDto("BCSP 2", "#SPLUSD4951B"),
            SemesterDto("BCSP 3", "#SPLUS42C545"),
            SemesterDto("BCSP 4", "#SPLUS4BB425")
        )
        coEvery { semestersExtractor.extractSemesters(MockEvaDispatcher.mainPageHtml) } returns semesters

        val result = runBlocking { uut.findEvaIdByToken("BCSP1") }

        assertThat(result).isEqualTo("#SPLUS42C544")
    }

    @Test
    fun `should find eva id by token when semester does not exist`() {
        val semesters = listOf(
            SemesterDto("BCSP 1", "#SPLUS42C544"),
            SemesterDto("BCSP 2", "#SPLUSD4951B"),
            SemesterDto("BCSP 3", "#SPLUS42C545"),
            SemesterDto("BCSP 4", "#SPLUS4BB425")
        )
        coEvery { semestersExtractor.extractSemesters(MockEvaDispatcher.mainPageHtml) } returns semesters

        val result = runBlocking { uut.findEvaIdByToken("BCSP5") }

        assertThat(result).isNull()
    }
}