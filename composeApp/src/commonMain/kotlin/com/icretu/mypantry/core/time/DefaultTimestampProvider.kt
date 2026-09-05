package com.icretu.mypantry.core.time

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class DefaultTimestampProvider : TimestampProvider {
    @OptIn(ExperimentalTime::class)
    override fun nowLocalDate(): LocalDate =
        Clock.System
            .now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date

    @OptIn(ExperimentalTime::class)
    override fun nowEpochMillis(): Long =
        Clock.System.now().toEpochMilliseconds()
}
