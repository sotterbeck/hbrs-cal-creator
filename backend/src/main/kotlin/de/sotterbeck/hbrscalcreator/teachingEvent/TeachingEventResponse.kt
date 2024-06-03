package de.sotterbeck.hbrscalcreator.teachingEvent

data class TeachingEventResponse(
    val data: List<EventModel>
) {

    data class EventModel(
        val id: String,
        val semester: String,
        val title: String,
        val module: String,
        val day: String,
        val startTime: String,
        val endTime: String,
        val room: String,
        val instructor: String,
        val group: String? = null,
        val types: Set<EventType>
    )

    data class EventType(
        val name: String,
        val token: String
    )
}