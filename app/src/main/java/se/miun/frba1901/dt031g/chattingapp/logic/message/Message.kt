package se.miun.frba1901.dt031g.chattingapp.logic.message

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message (
    @ColumnInfo(name = "from_user") val from: Int?,
    @ColumnInfo(name = "to_user") val to: Int?,
    @ColumnInfo(name = "text") val text: String?
){
    @PrimaryKey(autoGenerate = true) var messageID: Int = 0
}