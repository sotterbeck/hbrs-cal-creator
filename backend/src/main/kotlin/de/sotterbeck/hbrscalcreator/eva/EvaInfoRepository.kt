package de.sotterbeck.hbrscalcreator.eva

interface EvaInfoRepository {

    suspend fun findTermId(): String

    suspend fun findAllWeeks(): List<Int>

    suspend fun findAllSemesters(): List<SemesterDto>

    suspend fun findSemesterById(id: String): SemesterDto

    /**
     * Returns the eva id for the given semester token.
     *
     * The semester token is the name of the semester without whitespaces.
     *
     * @param token The token of the semester to get the eva id for.
     * @return The eva id for the given semester token or `null` if no semester with the given token exists.
     */
    suspend fun findEvaIdByToken(token: String): String?
}