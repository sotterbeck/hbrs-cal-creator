package de.sotterbeck.hbrscalcreator.teachingEvent

interface GetAllTeachingEventsInteractor {

    suspend operator fun invoke(): TeachingEventResponse

}