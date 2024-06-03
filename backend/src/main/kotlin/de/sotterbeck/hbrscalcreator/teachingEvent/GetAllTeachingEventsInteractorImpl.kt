package de.sotterbeck.hbrscalcreator.teachingEvent

import de.sotterbeck.hbrscalcreator.eva.EvaInfoRepository

class GetAllTeachingEventsInteractorImpl(
    private val teachingEventRepository: TeachingEventRepository,
    private val evaInfoRepository: EvaInfoRepository,
    private val teachingEventPresenter: TeachingEventPresenter
) : GetAllTeachingEventsInteractor {

    override suspend fun invoke(): TeachingEventResponse {
        val semesterIds = evaInfoRepository.findAllSemesters().map { it.evaId }

        val teachingEvents = teachingEventRepository.findAllTeachingEvents(semesterIds)

        val formattedTeachingEvents = teachingEventPresenter.presentSuccess(teachingEvents)
        return TeachingEventResponse(
            data = formattedTeachingEvents
        )
    }

}