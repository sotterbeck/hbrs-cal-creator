package de.sotterbeck.hbrscalcreator.reader

import com.sksamuel.aedile.core.cacheBuilder
import kotlin.time.Duration.Companion.hours

class CachedTimeTableReader(private val delegate: TimeTableReader) : TimeTableReader {

    private val cache = cacheBuilder<String, List<Map<String, String>>> {
        expireAfterWrite = 1.hours
    }.build()

    override suspend fun readTimetable(
        termId: String,
        semesterId: String,
        weeks: List<Int>
    ): List<Map<String, String>> {
        val key = keyFor(termId, semesterId, weeks)
        return cache.get(key) {
            delegate.readTimetable(termId, semesterId, weeks)
        }
    }

    private fun keyFor(
        termId: String,
        semesterId: String,
        weeks: List<Int>
    ) = "$termId-$semesterId-${weeks.joinToString(",")}"
}