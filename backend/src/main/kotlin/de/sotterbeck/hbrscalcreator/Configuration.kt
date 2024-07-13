package de.sotterbeck.hbrscalcreator

import com.sksamuel.aedile.core.Cache
import com.sksamuel.aedile.core.cacheBuilder
import de.sotterbeck.hbrscalcreator.downloader.TimeTableXlsSource
import de.sotterbeck.hbrscalcreator.eva.*
import de.sotterbeck.hbrscalcreator.eva.htmlParsing.EvaExtractor
import de.sotterbeck.hbrscalcreator.eva.htmlParsing.EvaExtractorFactory
import de.sotterbeck.hbrscalcreator.ical.BiWeeklyICalGenerator
import de.sotterbeck.hbrscalcreator.ical.CreateICalInteractor
import de.sotterbeck.hbrscalcreator.ical.CreateICalInteractorImpl
import de.sotterbeck.hbrscalcreator.ical.ICalGenerator
import de.sotterbeck.hbrscalcreator.reader.ApachePOITimeTableReader
import de.sotterbeck.hbrscalcreator.reader.CachedTimeTableReader
import de.sotterbeck.hbrscalcreator.reader.TimeTableReader
import de.sotterbeck.hbrscalcreator.teachingEvent.*
import de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator.CombinedTeachingEventKeyGenerator
import de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator.HashTeachingEventKeyGenerator
import de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator.TeachingEventKeyGenerator
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.TeachingEventMapper
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.TeachingEventParsingFactory
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.impl.DefaultTeachingEventParsingFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory
import kotlin.time.Duration.Companion.hours

@Configuration
class Configuration {

    @Value("\${eva.url}")
    private lateinit var evaUrl: String

    @Value("\${teachingEvent.id-generator.type}")
    private lateinit var idGeneratorType: String

    @Value("\${teachingEvent.id-generator.hash.algorithm}")
    private lateinit var idGeneratorAlgorithm: String


    @Bean
    fun webClient(): WebClient {
        val uriBuilderFactory = DefaultUriBuilderFactory(evaUrl)
        uriBuilderFactory.encodingMode = DefaultUriBuilderFactory.EncodingMode.NONE

        return WebClient.builder()
            .baseUrl(evaUrl)
            .uriBuilderFactory(uriBuilderFactory)
            .build()
    }

    @Bean
    fun evaHtmlCache(): Cache<String, String> {
        return cacheBuilder<String, String>() {
            expireAfterWrite = 1.hours
        }.build()
    }

    @Bean
    fun timeTableCache(): Cache<String, ByteArray> {
        return cacheBuilder<String, ByteArray>() {
            expireAfterWrite = 24.hours
        }.build()
    }

    @Bean
    fun evaExtractorFactory(): EvaExtractorFactory {
        return EvaExtractor.extractors()
    }


    @Bean
    fun timeTableReaderImpl(timeTableSource: TimeTableXlsSource): TimeTableReader {
        return ApachePOITimeTableReader(timeTableSource = timeTableSource)
    }

    @Primary
    @Bean
    fun timeTableReader(@Qualifier("timeTableReaderImpl") timeTableReader: TimeTableReader): TimeTableReader {
        return CachedTimeTableReader(timeTableReader)
    }

    @Bean
    fun parsingFactory(): TeachingEventParsingFactory {
        return DefaultTeachingEventParsingFactory()
    }

    @Bean
    fun teachingEventIdGenerator(parsingFactory: TeachingEventParsingFactory): TeachingEventKeyGenerator {
        return when (idGeneratorType) {
            "combined" -> CombinedTeachingEventKeyGenerator()
            "hash" -> HashTeachingEventKeyGenerator(idGeneratorAlgorithm)
            else -> error("Unknown id generator type: $idGeneratorType")
        }
    }

    @Bean
    fun teachingEventMapper(parsingFactory: TeachingEventParsingFactory): TeachingEventMapper {
        return parsingFactory.createDtoMapper()
    }

    @Bean
    fun teachingEventPresenter(
        parsingFactory: TeachingEventParsingFactory,
        teachingEventIdGenerator: TeachingEventKeyGenerator
    ): TeachingEventPresenter {
        return TeachingEventPresenterImpl(parsingFactory, teachingEventIdGenerator)
    }

    @Bean
    fun courseOfStudyPresenter(): CourseOfStudyPresenter {
        return DefaultCourseOfStudyPresenter()
    }

    @Bean
    fun getAllTeachingEventsInteractor(
        teachingEventRepository: TeachingEventRepository,
        evaInfoRepository: EvaInfoRepository,
        teachingEventPresenter: TeachingEventPresenter
    ): GetAllTeachingEventsInteractor {
        return GetAllTeachingEventsInteractorImpl(
            teachingEventRepository = teachingEventRepository,
            evaInfoRepository = evaInfoRepository,
            teachingEventPresenter = teachingEventPresenter
        )
    }

    @Bean
    fun getAllCoursesOfStudyInteractor(
        evaInfoRepository: EvaInfoRepository,
        courseOfStudyPresenter: CourseOfStudyPresenter
    ): GetAllCoursesOfStudyInteractor {
        return GetAllCoursesOfStudyInteractorImpl(evaInfoRepository, courseOfStudyPresenter)
    }

    @Bean
    fun getAllSemesterNamesInteractor(
        evaInfoRepository: EvaInfoRepository
    ): GetAllSemesterNamesInteractor {
        return GetAllSemesterNamesInteractorImpl(evaInfoRepository)
    }

    @Bean
    fun getTeachingEventsInteractor(
        teachingEventRepository: TeachingEventRepository,
        evaInfoRepository: EvaInfoRepository,
        teachingEventPresenter: TeachingEventPresenter
    ): GetTeachingEventsInteractor {
        return GetTeachingEventsInteractorImpl(
            teachingEventRepository = teachingEventRepository,
            evaInfoRepository = evaInfoRepository,
            teachingEventPresenter = teachingEventPresenter
        )
    }

    @Bean
    fun iCalGenerator(): ICalGenerator {
        return BiWeeklyICalGenerator()
    }

    @Bean
    fun createICalInteractor(
        teachingEventRepository: TeachingEventRepository,
        iCalGenerator: ICalGenerator
    ): CreateICalInteractor {
        return CreateICalInteractorImpl(
            teachingEventRepository = teachingEventRepository,
            iCalGenerator = iCalGenerator
        )
    }
}