package com.icretu.mypantry.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

object DateUtils {
    fun formatDate(date: LocalDate?): String {
        if (date == null) return "No expiration date"

        val day = date.dayOfMonth.toString().padStart(2, '0')
        val month = date.monthNumber.toString().padStart(2, '0')

        return "$day.$month.${date.year}"
    }

    @OptIn(ExperimentalTime::class)
    fun millisToLocalDate(millis: Long?): LocalDate? {
        if (millis == null) return null

        return Instant
            .fromEpochMilliseconds(millis)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
    }

    @OptIn(ExperimentalTime::class)
    fun localDateToMillis(date: LocalDate?): Long? {
        if (date == null) return null

        return date
            .toString()
            .let { LocalDate.parse(it) }
            .atStartOfDayIn(TimeZone.currentSystemDefault())
            .toEpochMilliseconds()
    }
}
