package se.miun.frba1901.dt031g.chattingapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import se.miun.frba1901.dt031g.chattingapp.logic.message.Message
@Dao
interface MessageDAO {
    @Query("SELECT * FROM message WHERE from_user=:fromUser")
    fun getMessagesFromUser(fromUser: String): List<Message>

    @Query("SELECT * FROM message WHERE from_user=:fromUser and to_user=:toUser")
    fun getMessagesFromUserToUser(fromUser: Int, toUser: Int): List<Message>

    @Query("SELECT * FROM message WHERE messageID=:messageID")
    fun getMessage(messageID: Int): Message?

    @Insert
    fun insertAll(vararg messages: Message)

    @Delete
    fun delete(message: Message)
}