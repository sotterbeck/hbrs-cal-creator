package de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator

import de.sotterbeck.hbrscalcreator.teachingEvent.TeachingEventDto
import java.security.MessageDigest

class HashTeachingEventKeyGenerator(private val algorithm: String) : TeachingEventKeyGenerator {

    override fun generateKey(teachingEvent: TeachingEventDto): String {
        val teachingEventString = teachingEvent.toString()

        return teachingEventString.hashed(algorithm)
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun String.hashed(algorithm: String = "SHA-256"): String {
        val md = MessageDigest.getInstance(algorithm)
        val digest = md.digest(this.toByteArray())
        return digest.toHexString()
    }
}