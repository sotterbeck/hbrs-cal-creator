package de.sotterbeck.hbrscalcreator.teachingEvent

import de.sotterbeck.hbrscalcreator.eva.EvaInfoRepository
import de.sotterbeck.hbrscalcreator.reader.TimeTableReader
import de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator.TeachingEventKeyGenerator
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.TeachingEventMapper
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Repository

@Repository
class TeachingEventRepositoryImpl(
    private val reader: TimeTableReader,
    private val mapper: TeachingEventMapper,
    private val idGenerator: TeachingEventKeyGenerator,
    private val evaInfoRepository: EvaInfoRepository
) : TeachingEventRepository {

    override suspend fun findAllTeachingEvents(semesters: List<String>): List<TeachingEventDto> {
        val termId = evaInfoRepository.findTermId()
        val weeks = evaInfoRepository.findAllWeeks()

        return coroutineScope {
            semesters.map { semesterId ->
                async {
                    val semester = evaInfoRepository.findSemesterById(semesterId)
                    reader.readTimetable(termId, semesterId, weeks).map { mapper.toDto(it, semester.name) }
                }
            }.awaitAll().flatten()
                .sortedWith(compareBy(TeachingEventDto::day, TeachingEventDto::startTime))
        }
    }

    override suspend fun findTeachingEventById(id: String): TeachingEventDto? {
        val semesterIds = evaInfoRepository.findAllSemesters().map { it.evaId }
        val teachingEvents = findAllTeachingEvents(semesterIds)

        return teachingEvents.find { idGenerator.generateKey(it) == id }
    }

}