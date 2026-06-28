package com.icretu.mypantry.domain.model

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

enum class ExpiryStatus {
    NONE,
    OK,
    EXPIRING_SOON,
    EXPIRED
}

@OptIn(ExperimentalTime::class)
fun LocalDate?.toExpiryStatus(): ExpiryStatus {
    if (this == null) return ExpiryStatus.NONE

    val today = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    val thirtyDaysFromNow = today.plus(30, DateTimeUnit.DAY)

    return when {
        this < today -> ExpiryStatus.EXPIRED
        this <= thirtyDaysFromNow -> ExpiryStatus.EXPIRING_SOON
        else -> ExpiryStatus.OK
    }
}
