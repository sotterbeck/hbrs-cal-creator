package de.sotterbeck.hbrscalcreator.teachingEvent

interface TeachingEventRepository {

    suspend fun findAllTeachingEvents(semesters: List<String>): List<TeachingEventDto>

    suspend fun findTeachingEventById(id: String): TeachingEventDto?

}