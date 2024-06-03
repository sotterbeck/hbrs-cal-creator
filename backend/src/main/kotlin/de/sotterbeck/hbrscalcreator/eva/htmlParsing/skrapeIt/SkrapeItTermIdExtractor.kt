package de.sotterbeck.hbrscalcreator.eva.htmlParsing.skrapeIt

import de.sotterbeck.hbrscalcreator.eva.htmlParsing.TermIdExtractor
import it.skrape.core.htmlDocument
import it.skrape.selects.html5.input

class SkrapeItTermIdExtractor : TermIdExtractor {

    override suspend fun extractTermId(html: String): String {
        val token = try {
            htmlDocument(html) {
                input {
                    withAttributes = listOf(
                        "name" to "term",
                        "type" to "hidden"
                    )
                    findFirst {
                        attribute("value")
                    }
                }
            }
        } catch (e: Exception) {
            throw IllegalStateException("Could not extract term token, maybe the site was updated?", e)
        }

        return token
    }
}