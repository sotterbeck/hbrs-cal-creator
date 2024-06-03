package de.sotterbeck.hbrscalcreator.teachingEvent

interface GetTeachingEventsInteractor {

    suspend operator fun invoke(request: Request): TeachingEventResponse

    data class Request(
        /**
         * The semesters to get the teaching events for. The semesters are identified by their token.
         * The token is the name of the semester without whitespaces.
         *
         * Example: `BI1`, `BI2`, `BCSP1`, `Zusatzveranstaltungen`.
         */
        val semester: List<String>
    )
}