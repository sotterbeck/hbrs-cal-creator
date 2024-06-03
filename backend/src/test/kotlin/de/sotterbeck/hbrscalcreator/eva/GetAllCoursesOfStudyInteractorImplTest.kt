package de.sotterbeck.hbrscalcreator.eva

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetAllCoursesOfStudyInteractorImplTest {

    private val evaInfoRepository = mockk<EvaInfoRepository>()
    private val courseOfStudyPresenter = mockk<CourseOfStudyPresenter>()

    private val semesters = listOf(
        SemesterDto("BCSP 1", "#SPLUS42C544"),
        SemesterDto("BCSP 2", "#SPLUSD4951B"),
        SemesterDto("BCSP 3", "#SPLUS42C545"),
        SemesterDto("BCSP 4", "#SPLUS4BB425"),
        SemesterDto("BI 1", "#SPLUS42C53D"),
        SemesterDto("BI 2", "BCS2"),
        SemesterDto("BI 3", "#SPLUS42C53E"),
        SemesterDto("BI 4", "BCS4"),
        SemesterDto("BI 5", "#SPLUS42C53F"),
        SemesterDto("BWI 1", "#SPLUS42C541"),
        SemesterDto("BWI 2", "BBIS2"),
        SemesterDto("BWI 4", "BBIS4"),
        SemesterDto("BWI 5", "#SPLUS42C543"),
        SemesterDto("MAS 1", "MAS1"),
        SemesterDto("MAS 2", "MAS2"),
        SemesterDto("MAS 3", "MAS3"),
        SemesterDto("MAS 4", "#SPLUSE4D31E"),
        SemesterDto("MCSP 1", "#SPLUS5E854A"),
        SemesterDto("MCSP 2", "#SPLUSECE0E1"),
        SemesterDto("MI 1", "MCS1"),
        SemesterDto("MI 2", "MCS2"),
        SemesterDto("MI 3", "MCS3"),
        SemesterDto("MKSN", "#SPLUS4222DA"),
        SemesterDto("MVG 1", "#SPLUS157A20"),
        SemesterDto("MVG 2", "#SPLUSAC7DD0"),
        SemesterDto("MVG 3", "#SPLUSAC7DD1"),
        SemesterDto("Zusatzveranstaltungen", "#SPLUS382187")
    )

    @BeforeEach
    fun setUp() {
        coEvery { evaInfoRepository.findAllSemesters() } returns semesters
        every { courseOfStudyPresenter.presentCoursesOfStudy(semesters) } returns GetAllCoursesOfStudyInteractor.Response(
            data = emptyList()
        )
    }

    @Test
    fun `should find all semesters and present them`() {
        val uut = GetAllCoursesOfStudyInteractorImpl(evaInfoRepository, courseOfStudyPresenter)

        runBlocking { uut() }

        coVerify { evaInfoRepository.findAllSemesters() }
        verify { courseOfStudyPresenter.presentCoursesOfStudy(eq(semesters)) }
    }
}