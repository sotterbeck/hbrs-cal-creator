package de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator

import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventDto

interface TeachingEventKeyGenerator {

    fun generateKey(teachingEvent: TeachingEventDto): String

}