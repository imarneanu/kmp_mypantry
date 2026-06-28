package com.icretu.mypantry.data.local

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

class LocalDateConverter {

    @TypeConverter
    fun fromString(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun localDateToString(date: LocalDate?): String? {
        return date?.toString()
    }
}
