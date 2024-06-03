package de.sotterbeck.hbrscalcreator.eva.htmlParsing

interface EvaExtractorFactory {

    fun createTermIdExtractor(): TermIdExtractor

    fun createWeeksExtractor(): WeeksExtractor

    fun createSemestersExtractor(): SemestersExtractor

}
