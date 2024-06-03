package de.sotterbeck.hbrscalcreator.eva

class GetAllSemesterNamesInteractorImpl(
    private val evaInfoRepository: EvaInfoRepository
) : GetAllSemesterNamesInteractor {

    override suspend fun invoke(): GetAllSemesterNamesInteractor.Response {
        val semesters = evaInfoRepository.findAllSemesters()

        return GetAllSemesterNamesInteractor.Response(
            data = semesters.map { it.name }
        )
    }
}