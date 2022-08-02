package se.miun.frba1901.dt031g.chattingapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import se.miun.frba1901.dt031g.chattingapp.logic.friend.Friendship
import se.miun.frba1901.dt031g.chattingapp.logic.message.Message
import se.miun.frba1901.dt031g.chattingapp.logic.user.User
import se.miun.frba1901.dt031g.chattingapp.persistence.MessageDAO

@Database(entities = [Message::class, Friendship::class, User::class], version = 1)
@TypeConverters(Converters::class)
abstract class DatabaseWithDAO: RoomDatabase() {
    abstract fun messageDAO(): MessageDAO
    abstract fun userDAO(): UserDAO
    abstract fun friendshipDAO(): FriendshipDAO
}