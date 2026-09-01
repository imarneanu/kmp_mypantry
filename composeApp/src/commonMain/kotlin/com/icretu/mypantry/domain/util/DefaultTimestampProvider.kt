package com.icretu.mypantry.domain.util

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class DefaultTimestampProvider : TimestampProvider {

    @OptIn(ExperimentalTime::class)
    override fun nowEpochMillis(): Long =
        Clock.System.now().toEpochMilliseconds()
}
