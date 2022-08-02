package se.miun.frba1901.dt031g.chattingapp.logic.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name = "name") val name: String,
){
    @PrimaryKey(autoGenerate = true)
    var userID: Int = 0

}