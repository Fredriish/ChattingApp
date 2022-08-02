package se.miun.frba1901.dt031g.chattingapp.persistence

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date?{
        return value?.let { Date(it) }
    }
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}