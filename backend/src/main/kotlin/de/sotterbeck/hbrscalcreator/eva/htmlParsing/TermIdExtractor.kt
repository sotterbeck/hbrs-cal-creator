package de.sotterbeck.hbrscalcreator.eva.htmlParsing

interface TermIdExtractor {

    suspend fun extractTermId(html: String): String
}