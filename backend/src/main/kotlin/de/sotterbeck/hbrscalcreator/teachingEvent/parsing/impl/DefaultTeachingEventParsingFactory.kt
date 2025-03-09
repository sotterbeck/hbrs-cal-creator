package de.sotterbeck.hbrscalcreator.teachingEvent.parsing.impl

import de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator.TeachingEventKeyGenerator
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.EventPropertyParser
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.TeachingEventMapper
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.TeachingEventParsingFactory
import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.TypeProvider

/**
 * Creates default components for parsing and mapping teaching events.
 *
 * The parsers and mappers only have tested support for the summer term of 2024 (term id `f685ba7c997816b8a0a640622adc862c`).
 *
 * To extend support for other terms, implement a new [TeachingEventParsingFactory] with updated parser and mapping implementations.
 */
class DefaultTeachingEventParsingFactory(private val keyGenerator: TeachingEventKeyGenerator) :
    TeachingEventParsingFactory {

    override fun createModuleParser(): EventPropertyParser {
        return DefaultModuleParser(
            types = DefaultTypeProvider.typeKeys
        )
    }

    override fun createEventTypeParser(): EventPropertyParser {
        return DefaultGroupParser()
    }

    override fun createTypeProvider(): TypeProvider {
        return DefaultTypeProvider
    }

    override fun createDtoMapper(): TeachingEventMapper {
        return DefaultTeachingEventMapper(keyGenerator)
    }
}