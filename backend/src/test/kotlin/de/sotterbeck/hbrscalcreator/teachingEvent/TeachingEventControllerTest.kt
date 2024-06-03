package de.sotterbeck.hbrscalcreator.teachingEvent

import assertk.assertAll
import de.sotterbeck.hbrscalcreator.ical.CreateICalInteractor
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class TeachingEventControllerTest() {

    private lateinit var uut: TeachingEventController
    private val getAllTeachingEvents = mockk<GetAllTeachingEventsInteractor>()
    private val getTeachingEvents = mockk<GetTeachingEventsInteractor>()
    private val createICal = mockk<CreateICalInteractor>()

    @BeforeEach
    fun setUp() {
        coEvery { getAllTeachingEvents() } returns TeachingEventResponse(
            listOf(
                TeachingEventResponse.EventModel(
                    id = "BI1-Event1-Instructor2-Di-09:45-11:15-V",
                    semester = "BI 1",
                    title = "Event 1 (V)",
                    module = "Event 1",
                    day = "Di",
                    startTime = "09:45",
                    endTime = "11:15",
                    room = "B456",
                    instructor = "Instructor 2",
                    types = setOf(
                        TeachingEventResponse.EventType(
                            name = "Vorlesung",
                            token = "V"
                        )
                    ),
                    group = null
                ),
            )
        )

        uut = TeachingEventController(
            getAllTeachingEventsInteractor = getAllTeachingEvents,
            getTeachingEventsInteractor = getTeachingEvents,
            createICalInteractor = createICal
        )
    }

    @Test
    fun `should return all teaching events from use case`() {
        val response = runBlocking { uut.allTeachingEvents() }

        assertAll {
            assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
            assertThat(response.body).isEqualTo(
                TeachingEventResponse(
                    listOf(
                        TeachingEventResponse.EventModel(
                            id = "BI1-Event1-Instructor2-Di-09:45-11:15-V",
                            semester = "BI 1",
                            title = "Event 1 (V)",
                            module = "Event 1",
                            day = "Di",
                            startTime = "09:45",
                            endTime = "11:15",
                            room = "B456",
                            instructor = "Instructor 2",
                            types = setOf(
                                TeachingEventResponse.EventType(
                                    name = "Vorlesung",
                                    token = "V"
                                )
                            ),
                            group = null
                        ),
                    )
                )
            )
        }
    }

    @Test
    fun `should return all teaching events from specified semesters when they exist`() {
        coEvery { getTeachingEvents(GetTeachingEventsInteractor.Request(semester = listOf("BI1"))) } returns TeachingEventResponse(
            listOf(
                TeachingEventResponse.EventModel(
                    id = "BI1-Event1-Instructor2-Di-09:45-11:15-V",
                    semester = "BI 1",
                    title = "Event 1 (V)",
                    module = "Event 1",
                    day = "Di",
                    startTime = "09:45",
                    endTime = "11:15",
                    room = "B456",
                    instructor = "Instructor 2",
                    types = setOf(
                        TeachingEventResponse.EventType(
                            name = "Vorlesung",
                            token = "V"
                        )
                    ),
                    group = null
                ),
            )
        )

        val response = runBlocking {
            uut.teachingEvents(GetTeachingEventsInteractor.Request(semester = listOf("BI1")))
        }

        assertAll {
            assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
            assertThat(response.body).isEqualTo(
                TeachingEventResponse(
                    listOf(
                        TeachingEventResponse.EventModel(
                            id = "BI1-Event1-Instructor2-Di-09:45-11:15-V",
                            semester = "BI 1",
                            title = "Event 1 (V)",
                            module = "Event 1",
                            day = "Di",
                            startTime = "09:45",
                            endTime = "11:15",
                            room = "B456",
                            instructor = "Instructor 2",
                            types = setOf(
                                TeachingEventResponse.EventType(
                                    name = "Vorlesung",
                                    token = "V"
                                )
                            ),
                            group = null
                        ),
                    )
                )
            )
        }
    }
}