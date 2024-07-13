package de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator

import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventDto
import java.time.format.TextStyle
import java.util.*

class CombinedTeachingEventKeyGenerator : TeachingEventKeyGenerator {

    override fun generateKey(teachingEvent: TeachingEventDto): String {
        val segments = listOf(
            teachingEvent.semester.replace(" ", ""),
            teachingEvent.eventTitle.pascalCased().withoutUmlauts().withoutSpecialChars(),
            teachingEvent.instructor.pascalCased().withoutUmlauts().withoutSpecialChars(),
            teachingEvent.day.getDisplayName(TextStyle.SHORT, Locale.GERMAN).replace(".", ""),
            teachingEvent.startTime.toString().withoutSpecialChars(),
            teachingEvent.endTime.toString().withoutSpecialChars(),
        )
        return segments.joinToString(separator = "-").replace("Ü", "U")
    }

    private fun String.pascalCased(): String {
        return this.split(" ").joinToString("") {
            it.replaceFirstChar { char -> char.uppercase() }
        }
    }

    private fun String.withoutUmlauts(): String {
        return this
            .replace("Ü", "ue")
            .replace("Ö", "oe")
            .replace("Ä", "ae")
            .replace("ß", "ss")
            .replace("ü", "ue")
            .replace("ö", "oe")
            .replace("ä", "ae")
    }

    private fun String.withoutSpecialChars(): String {
        return this.replace(Regex("[^A-Za-z0-9]"), "")
    }
}
