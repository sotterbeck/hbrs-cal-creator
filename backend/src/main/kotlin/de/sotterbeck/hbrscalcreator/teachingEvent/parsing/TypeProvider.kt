package de.sotterbeck.hbrscalcreator.teachingEvent.parsing

interface TypeProvider {

    val typeKeys: Set<String>

    fun typesForEvent(eventTitle: String): Set<EventType>
}