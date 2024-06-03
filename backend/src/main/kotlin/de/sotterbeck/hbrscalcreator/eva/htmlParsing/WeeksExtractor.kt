package de.sotterbeck.hbrscalcreator.eva.htmlParsing

interface WeeksExtractor {

    suspend fun extractWeeks(html: String): List<Int>

}