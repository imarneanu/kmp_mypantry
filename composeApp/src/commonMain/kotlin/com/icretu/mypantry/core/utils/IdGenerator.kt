package com.icretu.mypantry.core.utils

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class IdGenerator {
    @OptIn(ExperimentalUuidApi::class)
    fun generate(): String = Uuid.Companion.random().toString()
}
