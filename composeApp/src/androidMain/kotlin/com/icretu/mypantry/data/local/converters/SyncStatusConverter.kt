package com.icretu.mypantry.data.local.converters

import androidx.room.TypeConverter
import com.icretu.mypantry.core.sync.SyncStatus

class SyncStatusConverter {
    @TypeConverter
    fun syncStatusToString(value: SyncStatus): String = value.name

    @TypeConverter
    fun stringToSyncStatus(value: String): SyncStatus = SyncStatus.valueOf(value)
}
