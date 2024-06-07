package de.sotterbeck.hbrscalcreator.teachingEvent

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.containsExactlyInAnyOrder
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import de.sotterbeck.hbrscalcreator.eva.EvaInfoRepository
import de.sotterbeck.hbrscalcreator.eva.SemesterDto
import de.sotterbeck.hbrscalcreator.reader.TimeTableReader
import de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator.CombinedTeachingEventKeyGenerator
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.impl.DefaultTeachingEventMapper
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.impl.DefaultTeachingEventParsingFactory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class TeachingEventRepositoryImplTest {

    private val reader = mockk<TimeTableReader>()
    private val mapper = DefaultTeachingEventMapper
    private val parsingFactory = DefaultTeachingEventParsingFactory()
    private val evaInfoRepository = mockk<EvaInfoRepository>()

    private val uut = TeachingEventRepositoryImpl(
        reader = reader,
        mapper = mapper,
        idGenerator = CombinedTeachingEventKeyGenerator(parsingFactory),
        evaInfoRepository = evaInfoRepository,
    )

    @BeforeEach
    fun setUp() {
        coEvery { reader.readTimetable(any(), any(), any()) } returns listOf(
            mapOf(
                "day" to "Di",
                "startTime" to "09:45",
                "endTime" to "11:15",
                "room" to "B456",
                "event" to "Event 2 (V)",
                "period" to "08.04.2024-08.07.2024  (KW 15-28)",
                "instructor" to "Instructor 2"
            ),
            mapOf(
                "day" to "Mo",
                "startTime" to "08:00",
                "endTime" to "09:30",
                "room" to "A123",
                "event" to "Event 1 Gr.3 (Ü)",
                "period" to "08.04.2024-01.07.2024  (uKW (ab KW 15))",
                "instructor" to "Instructor 1"
            )
        )
        coEvery { evaInfoRepository.findTermId() } returns "1234"
        coEvery { evaInfoRepository.findAllWeeks() } returns listOf(1, 2, 3, 4, 5)
        coEvery { evaInfoRepository.findAllSemesters() } returns listOf(SemesterDto("BCSP 1", "#SPULS42C544"))
        coEvery { evaInfoRepository.findSemesterById(any()) } returns SemesterDto("BCSP 1", "#SPULS42C544")
    }

    @Test
    fun `should return all teaching events from the reader in the right order`() {
        val result = runBlocking { uut.findAllTeachingEvents(listOf("#SPLUS42C544")) }

        assertThat(result).containsExactly(
            TeachingEventDto(
                day = DayOfWeek.MONDAY,
                startTime = LocalTime.of(8, 0),
                endTime = LocalTime.of(9, 30),
               dateOfFirstOccurrence = LocalDate.of(2024, 4, 8),
                dateOfLastOccurrence = LocalDate.of(2024, 7, 1),
                room = "A123",
                eventTitle = "Event 1 Gr.3 (Ü)",
                period = "08.04.2024-01.07.2024  (uKW (ab KW 15))",
                instructor = "Instructor 1",
                semester = "BCSP 1"
            ),
            TeachingEventDto(
                day = DayOfWeek.TUESDAY,
                startTime = LocalTime.of(9, 45),
                endTime = LocalTime.of(11, 15),
                dateOfFirstOccurrence = LocalDate.of(2024, 4, 8),
                dateOfLastOccurrence = LocalDate.of(2024, 7, 8),
                room = "B456",
                eventTitle = "Event 2 (V)",
                period = "08.04.2024-08.07.2024  (KW 15-28)",
                instructor = "Instructor 2",
                semester = "BCSP 1"
            )
        )

    }

    @Test
    fun `should return null when teaching event cannot be found`() {
        val result = runBlocking { uut.findTeachingEventById("1234") }

        assertThat(result).isNull()
    }

    @Test
    fun `should return teaching event when it has been found`() {
        val id = "BCSP1-Event2-Instructor2-Di-09:45-11:15-V"

        val result = runBlocking { uut.findTeachingEventById(id) }

        assertThat(result).isNotNull()
    }
}