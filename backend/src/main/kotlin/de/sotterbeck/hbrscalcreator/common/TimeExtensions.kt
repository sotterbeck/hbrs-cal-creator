package de.sotterbeck.hbrscalcreator.common

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.toUtilDate(): java.util.Date {
    return java.util.Date.from(this.atZone(ZoneId.systemDefault()).toInstant())
}

fun LocalDate.toUtilDate(): java.util.Date {
    return java.util.Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
}