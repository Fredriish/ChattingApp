package se.miun.frba1901.dt031g.chattingapp.logic.friend

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(primaryKeys = ["from_user", "to_user"])
data class Friendship(
    @ColumnInfo(name = "from_user") val from: Int,
    @ColumnInfo(name = "to_user") val to: Int,
    @ColumnInfo(name = "friend_since") val friendSince: Date,
    @ColumnInfo(name = "pending") var pending: Boolean = true
)
