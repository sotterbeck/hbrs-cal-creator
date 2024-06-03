package de.sotterbeck.hbrscalcreator.eva

interface GetAllSemesterNamesInteractor {

        suspend operator fun invoke(): Response

        data class Response(
            val data: List<String>
        )
}