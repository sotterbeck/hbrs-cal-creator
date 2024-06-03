package de.sotterbeck.hbrscalcreator.eva

interface GetAllCoursesOfStudyInteractor {

    suspend operator fun invoke(): Response

    data class Response(
        val data: List<CourseOfStudyModel>
    )

    data class CourseOfStudyModel(
        val name: String,
        val abbreviation: String,
        val semesters: Set<Int>
    )
}
