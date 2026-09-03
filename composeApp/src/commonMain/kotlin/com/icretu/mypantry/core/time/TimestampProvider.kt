package com.icretu.mypantry.core.time

import kotlinx.datetime.LocalDate

interface TimestampProvider {
    fun nowLocalDate(): LocalDate

    fun nowEpochMillis(): Long
}
