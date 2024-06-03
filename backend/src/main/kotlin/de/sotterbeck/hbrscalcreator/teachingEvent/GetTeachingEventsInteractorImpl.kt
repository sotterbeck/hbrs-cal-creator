package de.sotterbeck.hbrscalcreator.teachingEvent

import de.sotterbeck.hbrscalcreator.eva.EvaInfoRepository

class GetTeachingEventsInteractorImpl(
    private val teachingEventRepository: TeachingEventRepository,
    private val evaInfoRepository: EvaInfoRepository,
    private val teachingEventPresenter: TeachingEventPresenter
) : GetTeachingEventsInteractor {

    override suspend fun invoke(request: GetTeachingEventsInteractor.Request): TeachingEventResponse {
        val evaIds = request.semester.map {
            evaInfoRepository.findEvaIdByToken(it) ?: teachingEventPresenter.presentFailure("Semester with token $it not found")
        }

        val teachingEvents = teachingEventRepository.findAllTeachingEvents(evaIds)
        val formattedTeachingEvents = teachingEventPresenter.presentSuccess(teachingEvents)

        return TeachingEventResponse(
            data = formattedTeachingEvents
        )
    }
}