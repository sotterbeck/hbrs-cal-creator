package de.sotterbeck.hbrscalcreator.eva

import assertk.assertThat
import assertk.assertions.containsExactlyInAnyOrder
import de.sotterbeck.hbrscalcreator.eva.GetAllCoursesOfStudyInteractor.CourseOfStudyModel
import org.junit.jupiter.api.Test

class DefaultCourseOfStudyPresenterTest {

    private val uut = DefaultCourseOfStudyPresenter()

    @Test
    fun `should combine all semesters into a list of courses of studies when they have multiple semesters`() {
        val semesters = listOf(
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
            SemesterDto("MVG 1", "#SPLUS157A20"),
            SemesterDto("MVG 2", "#SPLUSAC7DD0"),
            SemesterDto("MVG 3", "#SPLUSAC7DD1"),
        )

        val coursesOfStudy = uut.presentCoursesOfStudy(semesters).data

        assertThat(coursesOfStudy).containsExactlyInAnyOrder(
            CourseOfStudyModel(
                name = "Bachelor Cyber Security & Privacy",
                abbreviation = "BCSP",
                semesters = setOf(1, 2, 3, 4)
            ),
            CourseOfStudyModel(
                name = "Bachelor Informatik",
                abbreviation = "BI",
                semesters = setOf(1, 2, 3, 4, 5)
            ),
            CourseOfStudyModel(
                name = "Bachelor Wirtschaftsinformatik",
                abbreviation = "BWI",
                semesters = setOf(1, 2, 4, 5)
            ),
            CourseOfStudyModel(
                name = "Master Autonomous Systems",
                abbreviation = "MAS",
                semesters = setOf(1, 2, 3, 4)
            ),
            CourseOfStudyModel(
                name = "Master Cyber Security & Privacy",
                abbreviation = "MCSP",
                semesters = setOf(1, 2)
            ),
            CourseOfStudyModel(
                name = "Master Informatik",
                abbreviation = "MI",
                semesters = setOf(1, 2, 3)
            ),
            CourseOfStudyModel(
                name = "Master Visual Computing & Games Technology",
                abbreviation = "MVG",
                semesters = setOf(1, 2, 3)
            ),
        )
    }

    @Test
    fun `should combine courses of study with a single semester`() {
        val semesters = listOf(
            SemesterDto("MKSN", "#SPLUS4222DA"),
            SemesterDto("Zusatzveranstaltungen", "#SPLUS382187")
        )

        val coursesOfStudy = uut.presentCoursesOfStudy(semesters).data

        assertThat(coursesOfStudy).containsExactlyInAnyOrder(
            CourseOfStudyModel(
                name = "Master Communication Systems and Networks",
                abbreviation = "MKSN",
                semesters = setOf(1)
            ),
            CourseOfStudyModel(
                name = "Zusatzveranstaltungen",
                abbreviation = "Zusatzveranstaltungen",
                semesters = setOf(1)
            )
        )
    }
}