package de.sotterbeck.hbrscalcreator.teachingEvent

import com.sksamuel.aedile.core.cacheBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import kotlin.time.Duration.Companion.hours

@Primary
@Component
/**
 * Adds simple caching to a [TeachingEventRepository] implementation.
 * Note that not all methods are cached, only the ones that are expected to be called frequently like [findTeachingEventById].
 *
 * @param delegate The [TeachingEventRepository] to delegate to.
 */
class CachedTeachingEventRepository(
    @Qualifier("teachingEventRepositoryImpl") private val delegate: TeachingEventRepository
) : TeachingEventRepository {

    private val eventIdCache = cacheBuilder<String, TeachingEventDto> {
        expireAfterWrite = 12.hours
    }.build()

    override suspend fun findAllTeachingEvents(semesters: List<String>): List<TeachingEventDto> {
        return delegate.findAllTeachingEvents(semesters)
    }

    override suspend fun findTeachingEventById(id: String): TeachingEventDto? {
        return eventIdCache.getOrNull(id) {
            delegate.findTeachingEventById(id)
        }
    }

}