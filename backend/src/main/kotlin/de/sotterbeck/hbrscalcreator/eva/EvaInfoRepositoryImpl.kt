package de.sotterbeck.hbrscalcreator.eva

import de.sotterbeck.hbrscalcreator.eva.htmlParsing.EvaExtractorFactory
import org.springframework.stereotype.Repository

@Repository
class EvaInfoRepositoryImpl(
    extractorFactory: EvaExtractorFactory,
    private val evaHtmlSource: EvaHtmlSource
) : EvaInfoRepository {

    private val termIdExtractor = extractorFactory.createTermIdExtractor()
    private val weeksExtractor = extractorFactory.createWeeksExtractor()
    private val semestersExtractor = extractorFactory.createSemestersExtractor()

    private suspend fun fetchHtml(): String {
        return evaHtmlSource.downloadEvaSiteHtml()
    }

    override suspend fun findTermId(): String {
        val html = fetchHtml()
        return termIdExtractor.extractTermId(html)
    }

    override suspend fun findAllWeeks(): List<Int> {
        val html = fetchHtml()
        return weeksExtractor.extractWeeks(html)
    }

    override suspend fun findAllSemesters(): List<SemesterDto> {
        val html = fetchHtml()
        return semestersExtractor.extractSemesters(html)
    }

    override suspend fun findSemesterById(id: String): SemesterDto {
        return findAllSemesters().first { it.evaId == id }
    }

    override suspend fun findEvaIdByToken(token: String): String? {
        return findAllSemesters().firstOrNull { it.name.replace(" ", "") == token }?.evaId
    }
}