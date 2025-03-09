package de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator

interface TeachingEventKeyGenerator {

    fun generateKey(teachingEvent: Map<String, String>): String

}