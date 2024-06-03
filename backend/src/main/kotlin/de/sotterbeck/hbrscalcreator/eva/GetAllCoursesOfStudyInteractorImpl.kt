package de.sotterbeck.hbrscalcreator.eva

class GetAllCoursesOfStudyInteractorImpl(
    private val evaInfoRepository: EvaInfoRepository,
    private val courseOfStudyPresenter: CourseOfStudyPresenter
) : GetAllCoursesOfStudyInteractor {


    override suspend operator fun invoke(): GetAllCoursesOfStudyInteractor.Response {
        val semesters = evaInfoRepository.findAllSemesters()

        return courseOfStudyPresenter.presentCoursesOfStudy(semesters)
    }
}