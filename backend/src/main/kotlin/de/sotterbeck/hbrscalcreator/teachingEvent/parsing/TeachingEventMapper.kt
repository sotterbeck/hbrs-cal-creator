package de.sotterbeck.hbrscalcreator.teachingEvent.parsing

import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventDto

/**
 * Maps a teaching event from the raw data to a DTO and assigns a unique ID.
 * It only does the mapping, no validation or formatting.
 *
 * Formatting is the responsibility of the [TeachingEventPresenter].
 */
fun interface TeachingEventMapper {

    fun toDto(teachingEvent: Map<String, String>, semester: String): TeachingEventDto

}
