package de.sotterbeck.hbrscalcreator.teachingEvent

interface TeachingEventPresenter {

    fun presentSuccess(teachingEvents: List<TeachingEventDto>): List<TeachingEventResponse.EventModel>

    fun presentFailure(message: String): Nothing

}
