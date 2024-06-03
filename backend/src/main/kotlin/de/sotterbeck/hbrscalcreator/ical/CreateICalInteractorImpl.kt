package de.sotterbeck.hbrscalcreator.ical

import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventRepository

class CreateICalInteractorImpl(
    private val teachingEventRepository: TeachingEventRepository,
    private val iCalGenerator: ICalGenerator
) : CreateICalInteractor {

    override suspend fun invoke(request: CreateICalInteractor.Request): CreateICalInteractor.Response {
        val events = request.event
        require(events.isNotEmpty()) { "Request must not be empty" }

        val teachingEvents = events.map {
            teachingEventRepository.findTeachingEventById(it)
                ?: throw IllegalArgumentException("Teaching event with id $it not found")
        }
        val semesters = teachingEvents.map { it.semester.replace(" ", "") }.distinct()

        val iCal = iCalGenerator.generateICal(teachingEvents)
        return CreateICalInteractor.Response(
            iCal = iCal,
            fileName = semesters.joinToString(separator = "_") + ".ics"
        )
    }
}