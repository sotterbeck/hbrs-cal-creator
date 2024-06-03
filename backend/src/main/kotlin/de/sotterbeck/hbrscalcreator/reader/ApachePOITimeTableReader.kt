package de.sotterbeck.hbrscalcreator.reader

import de.sotterbeck.hbrscalcreator.downloader.TimeTableXlsSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.slf4j.LoggerFactory

private const val HEADER_ROW_COUNT = 4

private const val DAY_COLUMN_INDEX = 0
private const val START_TIME_COLUMN_INDEX = 1
private const val END_TIME_COLUMN_INDEX = 2
private const val ROOM_COLUMN_INDEX = 3
private const val EVENT_COLUMN_INDEX = 4
private const val PERIOD_COLUMN_INDEX = 5
private const val INSTRUCTOR_COLUMN_INDEX = 6

class ApachePOITimeTableReader(
    private val timeTableSource: TimeTableXlsSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TimeTableReader {

    private val logger = LoggerFactory.getLogger(ApachePOITimeTableReader::class.java)

    /**
     * Reads the timetable for the given term and semester.
     *
     *
     * @return List of teaching events as maps with the following keys: day, startTime, endTime, room, event, period, instructor
     */
    override suspend fun readTimetable(
        termId: String,
        semesterId: String,
        weeks: List<Int>
    ): List<Map<String, String>> = withContext(dispatcher) {
        val file = timeTableSource.fetchTimeTableXls(termId, semesterId, weeks)

        val workbook = WorkbookFactory.create(file)
        val sheet = workbook.getSheetAt(0)
        val teachingEvents = mutableListOf<Map<String, String>>()

        for ((index, row) in sheet.withIndex()) {
            if (isHeader(index) || hasReachedEnd(row)) {
                continue
            }

            val day = row.getCell(DAY_COLUMN_INDEX).stringCellValue.ifEmpty {
                getDayForRow(index, sheet) ?: error("Could not find day for row $index")
            }

            val event = mapTeachingEventRow(day, row)

            teachingEvents.add(event)
        }

        workbook.close()

        logger.info("Read ${teachingEvents.size} teaching events in $semesterId")

        return@withContext teachingEvents
    }

    private fun mapTeachingEventRow(
        day: String,
        row: Row
    ): Map<String, String> = mapOf(
        "day" to day,
        "startTime" to row.getCellString(START_TIME_COLUMN_INDEX),
        "endTime" to row.getCellString(END_TIME_COLUMN_INDEX),
        "room" to row.getCellString(ROOM_COLUMN_INDEX),
        "event" to row.getCellString(EVENT_COLUMN_INDEX),
        "period" to row.getCellString(PERIOD_COLUMN_INDEX),
        "instructor" to row.getCellString(INSTRUCTOR_COLUMN_INDEX)
    )

    private fun getDayForRow(row: Int, sheet: Sheet): String? {
        assert(row > 0) { "Row must be greater than 0" }

        for (i in row downTo 0) {
            val day = sheet.getRow(i).getCell(0).stringCellValue
            if (day.isNotEmpty()) {
                return day
            }
        }
        return null
    }

    private fun isHeader(index: Int) = index < HEADER_ROW_COUNT

    private fun hasReachedEnd(row: Row) = row.getCell(START_TIME_COLUMN_INDEX).stringCellValue.isEmpty()

    private fun Row.getCellString(cellIndex: Int): String = getCell(cellIndex).stringCellValue
}