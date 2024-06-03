package de.sotterbeck.hbrscalcreator.eva

interface CourseOfStudyPresenter {

    fun presentCoursesOfStudy(semester: List<SemesterDto>): GetAllCoursesOfStudyInteractor.Response

}
