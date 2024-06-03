package de.sotterbeck.hbrscalcreator.eva.htmlParsing

import de.sotterbeck.hbrscalcreator.eva.htmlParsing.skrapeIt.SkrapeItEvaExtractorFactory

class EvaExtractor {
    companion object {
        fun extractors(parser: String = "skrapeit"): EvaExtractorFactory {
            return when (parser) {
                "skrapeit" -> SkrapeItEvaExtractorFactory()
                else -> throw IllegalArgumentException("Unknown parser: $parser")
            }
        }
    }
}