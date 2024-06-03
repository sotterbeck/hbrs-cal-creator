package de.sotterbeck.hbrscalcreator.teachingEvent.parsing

/**
 * Abstract factory for creating the components needed for parsing and mapping teaching events.
 */
interface TeachingEventParsingFactory {

    fun createModuleParser(): EventPropertyParser

    fun createEventTypeParser(): EventPropertyParser

    fun createTypeProvider(): TypeProvider

    fun createDtoMapper(): TeachingEventMapper

}