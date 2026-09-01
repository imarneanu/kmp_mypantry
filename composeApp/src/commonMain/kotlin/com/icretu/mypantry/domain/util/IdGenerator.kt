package com.icretu.mypantry.domain.util

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class IdGenerator {
    @OptIn(ExperimentalUuidApi::class)
    fun generate(): String = Uuid.random().toString()
}
