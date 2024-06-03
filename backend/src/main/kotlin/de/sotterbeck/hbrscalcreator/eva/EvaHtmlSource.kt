package de.sotterbeck.hbrscalcreator.eva

interface EvaHtmlSource {

    suspend fun downloadEvaSiteHtml(): String
}