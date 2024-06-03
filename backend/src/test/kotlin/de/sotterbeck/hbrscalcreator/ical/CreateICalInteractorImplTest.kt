package de.sotterbeck.hbrscalcreator.ical

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventDto
import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class CreateICalInteractorImplTest {

    private val teachingEventRepository = mockk<TeachingEventRepository>()
    private val iCalGenerator = mockk<ICalGenerator>()

    private lateinit var createICal: CreateICalInteractorImpl

    @BeforeEach
    fun setUp() {
        createICal = CreateICalInteractorImpl(teachingEventRepository, iCalGenerator)
    }

    @Test
    fun `should throw exception when request is empty`() {
        val request = CreateICalInteractor.Request(emptyList())

        assertThrows<IllegalArgumentException> {
            runBlocking { createICal(request) }
        }
    }

    @Test
    fun `should throw exception when teaching event not found`() {
        val request = CreateICalInteractor.Request(listOf("1"))
        coEvery { teachingEventRepository.findTeachingEventById("1") } returns null

        assertThrows<IllegalArgumentException> {
            runBlocking { createICal(request) }
        }
    }

    @Test
    fun `should return iCal when teaching event found`() {
        val request = CreateICalInteractor.Request(listOf("1"))
        val teachingEvent = TeachingEventDto(
            semester = "BCSP 1",
            day = DayOfWeek.MONDAY,
            startTime = LocalTime.of(8, 0),
            endTime = LocalTime.of(9, 30),
            startDate = LocalDate.of(2024, 4, 8),
            endDate = LocalDate.of(2024, 7, 1),
            room = "A123",
            eventTitle = "Event 1 Gr.3 (Ü)",
            period = "08.04.2024-01.07.2024  (uKW (ab KW 15))",
            instructor = "Instructor 1"
        )
        coEvery { teachingEventRepository.findTeachingEventById("1") } returns teachingEvent
        every { iCalGenerator.generateICal(listOf(teachingEvent)) } returns "iCal"

        val response = runBlocking { createICal(request) }

        assertThat(response.iCal).isEqualTo("iCal")
    }

    @Test
    fun `should return file name with semester`() {
        val request = CreateICalInteractor.Request(listOf("1"))
        val teachingEvent = TeachingEventDto(
            semester = "BCSP 1",
            day = DayOfWeek.MONDAY,
            startTime = LocalTime.of(8, 0),
            endTime = LocalTime.of(9, 30),
            startDate = LocalDate.of(2024, 4, 8),
            endDate = LocalDate.of(2024, 7, 1),
            room = "A123",
            eventTitle = "Event 1 Gr.3 (Ü)",
            period = "08.04.2024-01.07.2024  (uKW (ab KW 15))",
            instructor = "Instructor 1"
        )
        coEvery { teachingEventRepository.findTeachingEventById("1") } returns teachingEvent
        every { iCalGenerator.generateICal(listOf(teachingEvent)) } returns "iCal"

        val response = runBlocking { createICal(request) }

        assertThat(response.fileName).isEqualTo("BCSP1.ics")
    }
}