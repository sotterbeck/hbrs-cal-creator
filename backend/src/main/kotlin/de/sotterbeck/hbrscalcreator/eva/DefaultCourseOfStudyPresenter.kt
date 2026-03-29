package de.sotterbeck.hbrscalcreator.eva

class DefaultCourseOfStudyPresenter : CourseOfStudyPresenter {

    private val courseOfStudyNames = mapOf(
        "BCSP" to "Bachelor Cyber Security & Privacy",
        "BI" to "Bachelor Informatik",
        "BWI" to "Bachelor Wirtschaftsinformatik",
        "MAS" to "Master Autonomous Systems",
        "MCSP" to "Master Cyber Security & Privacy",
        "MI" to "Master Informatik",
        "MKSN" to "Master Communication Systems and Networks",
        "MVG" to "Master Visual Computing & Games Technology",
        "Zusatzveranstaltungen" to "Zusatzveranstaltungen"
    )

    private val emptyCourseOfStudy = GetAllCoursesOfStudyInteractor.CourseOfStudyModel(
        name = "",
        abbreviation = "",
        semesters = setOf()
    )

    override fun presentCoursesOfStudy(semester: List<SemesterDto>): GetAllCoursesOfStudyInteractor.Response {
        val coursesOfStudy = semester.map { presentSingleSemester(it) }
            .groupBy { it.abbreviation }
            .map { (_, groupedCoursesOfStudy) ->
                groupedCoursesOfStudy.fold(emptyCourseOfStudy) { acc, courseOfStudy ->
                    acc.copy(
                        name = courseOfStudy.name,
                        abbreviation = courseOfStudy.abbreviation,
                        semesters = acc.semesters + courseOfStudy.semesters
                    )
                }
            }

        return GetAllCoursesOfStudyInteractor.Response(coursesOfStudy)
    }

    private fun presentSingleSemester(semester: SemesterDto): GetAllCoursesOfStudyInteractor.CourseOfStudyModel {
        val semesterName = semester.name
        val abbreviation = semesterName.substringBefore(" ")
        val courseOfStudyName = getCourseOfStudyName(abbreviation)

        val semesterLabel = extractSemesterLabel(semesterName, abbreviation)

        return GetAllCoursesOfStudyInteractor.CourseOfStudyModel(
            name = courseOfStudyName,
            abbreviation = abbreviation,
            semesters = setOf(semesterLabel)
        )
    }

    private fun extractSemesterLabel(semesterName: String, abbreviation: String): String {
        val label = semesterName.removePrefix(abbreviation).trim()
        if (label.isNotEmpty()) {
            return label
        }

        val number = semesterName.substringAfterLast(" ").toIntOrNull()
        return number?.toString() ?: "1"
    }

    private fun getCourseOfStudyName(abbreviation: String): String {
        return courseOfStudyNames[abbreviation] ?: abbreviation
    }
}
