package de.sotterbeck.hbrscalcreator.teachingEvent

import de.sotterbeck.hbrscalcreator.eva.EvaInfoRepository
import de.sotterbeck.hbrscalcreator.eva.SemesterDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class GetTeachingEventsInteractorImplTest {

    private val teachingEventRepository = mockk<TeachingEventRepository>()
    private val evaInfoRepository = mockk<EvaInfoRepository>()

    private val presenter = mockk<TeachingEventPresenter>()

    private lateinit var interactor: GetTeachingEventsInteractor

    @BeforeEach
    fun setUp() {
        coEvery { evaInfoRepository.findAllSemesters() } returns listOf(
            SemesterDto("BI 1", "#SPLUS42C53D"),
            SemesterDto("BI 2", "BCS2")
        )

        every { presenter.presentSuccess(any()) } returns emptyList()
        every { presenter.presentFailure(any()) } throws IllegalStateException()

        coEvery { teachingEventRepository.findAllTeachingEvents(any()) } returns listOf(
            TeachingEventDto(
                day = DayOfWeek.MONDAY,
                startTime = LocalTime.of(8, 0),
                endTime = LocalTime.of(9, 30),
                startDate = LocalDate.of(2024, 4, 8),
                endDate = LocalDate.of(2024, 7, 1),
                room = "A123",
                eventTitle = "Programmierung in C Gr.3 (Ü)",
                period = "08.04.2024-01.07.2024  (uKW (ab KW 15))",
                instructor = "Instructor 1",
                semester = "BI 1"
            ),
            TeachingEventDto(
                day = DayOfWeek.TUESDAY,
                startTime = LocalTime.of(9, 45),
                endTime = LocalTime.of(11, 15),
                startDate = LocalDate.of(2024, 4, 8),
                endDate = LocalDate.of(2024, 7, 1),
                room = "B456",
                eventTitle = "Einführung in die Analysis (V)",
                period = "08.04.2024-08.07.2024  (KW 15-28)",
                instructor = "Instructor 2",
                semester = "BI 1"
            ),
            TeachingEventDto(
                day = DayOfWeek.WEDNESDAY,
                startTime = LocalTime.of(8, 0),
                endTime = LocalTime.of(9, 30),
                startDate = LocalDate.of(2024, 4, 8),
                endDate = LocalDate.of(2024, 7, 1),
                room = "B123",
                eventTitle = "Englisch Gr.4 (Vorlesung und Übung)",
                period = "08.04.2024-01.07.2024  (uKW (ab KW 15))",
                instructor = "Instructor 1",
                semester = "BI 2"
            ),
            TeachingEventDto(
                day = DayOfWeek.THURSDAY,
                startTime = LocalTime.of(9, 45),
                endTime = LocalTime.of(11, 15),
                startDate = LocalDate.of(2024, 4, 8),
                endDate = LocalDate.of(2024, 7, 1),
                room = "C456",
                eventTitle = "Intelligente Netze und Virtualisierung (Internetkommunikation) (V)",
                period = "08.04.2024-08.07.2024  (KW 15-28)",
                instructor = "Instructor 2",
                semester = "BI 2"
            )
        )

        coEvery { evaInfoRepository.findEvaIdByToken("BI1") } returns "#SPLUS42C53D"
        coEvery { evaInfoRepository.findEvaIdByToken("BI2") } returns "BCS2"
        coEvery { evaInfoRepository.findEvaIdByToken("BI3") } returns null

        interactor = GetTeachingEventsInteractorImpl(
            teachingEventRepository = teachingEventRepository,
            evaInfoRepository = evaInfoRepository,
            teachingEventPresenter = presenter
        )
    }

    @Test
    fun `should retrieve teaching events only for specified semesters when semesters exist`() {
        val request = GetTeachingEventsInteractor.Request(semester = listOf("BI1"))

        runBlocking { interactor(request) }

        coVerify { teachingEventRepository.findAllTeachingEvents(listOf("#SPLUS42C53D")) }
    }

    @Test
    fun `should present error when requested semester does not exist`() {
        val request = GetTeachingEventsInteractor.Request(semester = listOf("BI3"))

        try {
            runBlocking { interactor(request) }
        } catch (_: Exception) { }

        coVerify { presenter.presentFailure(any()) }
    }
}