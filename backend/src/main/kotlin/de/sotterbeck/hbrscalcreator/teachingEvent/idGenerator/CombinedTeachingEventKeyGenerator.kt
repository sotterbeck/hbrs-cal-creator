package de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator

import java.util.*

class CombinedTeachingEventKeyGenerator : TeachingEventKeyGenerator {

    override fun generateKey(teachingEvent: Map<String, String>): String {
        return teachingEvent.entries.joinToString(separator = "-") {
            it.value.withoutUmlauts()
                .withoutSpecialChars()
                .lowercase(Locale.getDefault())
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
