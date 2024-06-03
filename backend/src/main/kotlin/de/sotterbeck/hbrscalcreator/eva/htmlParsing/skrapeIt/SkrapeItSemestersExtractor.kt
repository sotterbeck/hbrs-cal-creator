package de.sotterbeck.hbrscalcreator.eva.htmlParsing.skrapeIt

import de.sotterbeck.hbrscalcreator.eva.SemesterDto
import de.sotterbeck.hbrscalcreator.eva.htmlParsing.SemestersExtractor
import it.skrape.core.htmlDocument
import it.skrape.selects.html5.option
import it.skrape.selects.html5.select

class SkrapeItSemestersExtractor : SemestersExtractor {

    override suspend fun extractSemesters(html: String): List<SemesterDto> {
        val semesters = try {
            htmlDocument(html) {
                select {
                    withId = "identifier_semester"
                    option {
                        findAll { filter { it.attribute("value").isNotBlank() } }.map {
                            SemesterDto(
                                evaId = it.attribute("value"),
                                name = it.text
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            throw IllegalStateException("Could not extract semesters, maybe the site was updated?", e)
        }

        return semesters
    }
}