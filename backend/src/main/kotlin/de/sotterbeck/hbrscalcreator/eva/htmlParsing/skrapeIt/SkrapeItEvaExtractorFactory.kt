package de.sotterbeck.hbrscalcreator.eva.htmlParsing.skrapeIt

import de.sotterbeck.hbrscalcreator.eva.htmlParsing.EvaExtractorFactory
import de.sotterbeck.hbrscalcreator.eva.htmlParsing.SemestersExtractor
import de.sotterbeck.hbrscalcreator.eva.htmlParsing.TermIdExtractor
import de.sotterbeck.hbrscalcreator.eva.htmlParsing.WeeksExtractor

class SkrapeItEvaExtractorFactory(
) : EvaExtractorFactory {

    override fun createTermIdExtractor(): TermIdExtractor {
        return SkrapeItTermIdExtractor()
    }

    override fun createWeeksExtractor(): WeeksExtractor {
        return SkrapeItWeeksExtractor()
    }

    override fun createSemestersExtractor(): SemestersExtractor {
        return SkrapeItSemestersExtractor()
    }
}