package de.sotterbeck.hbrscalcreator.ical

interface CreateICalInteractor {

    suspend operator fun invoke(request: Request): Response

    data class Request(
        val event: List<String>
    )

    data class Response(
        val iCal: String,
        val fileName: String
    )
}