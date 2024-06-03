package de.sotterbeck.hbrscalcreator.teachingEvent.parsing.impl

import de.sotterbeck.hbrscalcreator.teachingEvent.parsing.EventPropertyParser

class DefaultGroupParser : EventPropertyParser {

    override fun parse(eventTitle: String): String {
        if (!eventTitle.contains("Gr.")) {
            return ""
        }

        return eventTitle
            .substringAfter(" Gr.")
            .trimStart()
            .substringBefore(" ")
    }
}