package de.sotterbeck.hbrscalcreator.eva.htmlParsing.skrapeIt

import de.sotterbeck.hbrscalcreator.eva.htmlParsing.WeeksExtractor
import it.skrape.core.htmlDocument
import it.skrape.matchers.toContain
import it.skrape.selects.html5.option
import it.skrape.selects.html5.select

class SkrapeItWeeksExtractor : WeeksExtractor {

    override suspend fun extractWeeks(html: String): List<Int> {
        val weeksValue = try {
            htmlDocument(html) {
                select {
                    withId = "input_weeks"
                    option {
                        findFirst {
                            text toContain "gesamt"
                            attribute("value")
                        }
                    }
                }
            }
        } catch (e: Exception) {
            throw IllegalStateException("Could not extract weeks, maybe the site was updated?", e)
        }

        return weeksValue.split(";").map { it.toInt() }
    }
}