package de.sotterbeck.hbrscalcreator.downloader

import java.io.InputStream

interface TimeTableXlsSource {

    suspend fun fetchTimeTableXls(termId: String, semesterId: String, weeks: List<Int>): InputStream

}