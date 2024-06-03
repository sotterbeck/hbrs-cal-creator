package de.sotterbeck.hbrscalcreator.eva.htmlParsing

import de.sotterbeck.hbrscalcreator.eva.SemesterDto

interface SemestersExtractor {

    suspend fun extractSemesters(html: String): List<SemesterDto>

}