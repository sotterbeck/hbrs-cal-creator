package de.sotterbeck.hbrscalcreator.reader

interface TimeTableReader {


    /**
     * Reads the timetable from the given file and returns a list of teaching events.
     *
     * @return List of teaching events as maps depending on the timetable format
     */
    suspend fun readTimetable(termId: String, semesterId: String, weeks: List<Int>): List<Map<String, String>>
}