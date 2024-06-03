package de.sotterbeck.hbrscalcreator.teachingEvent.parsing.impl

import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.EventPropertyParser

class DefaultModuleParser(private val types: Set<String>) : EventPropertyParser {

    override fun parse(eventTitle: String): String {
        val firstTitleSegment = eventTitle.substringBefore("Gr.").trim()
        for (type in types) {
            if (firstTitleSegment.contains(type)) {
                return firstTitleSegment.replace(type, "").trim()
            }
        }
        return firstTitleSegment
    }
}