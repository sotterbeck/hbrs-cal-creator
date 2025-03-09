package de.sotterbeck.hbrscalcreator.teachingEvent.idGenerator

import java.security.MessageDigest

class HashTeachingEventKeyGenerator(private val algorithm: String) : TeachingEventKeyGenerator {

    override fun generateKey(teachingEvent: Map<String, String>): String {
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