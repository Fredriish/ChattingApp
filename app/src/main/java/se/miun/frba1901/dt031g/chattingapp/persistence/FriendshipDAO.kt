package se.miun.frba1901.dt031g.chattingapp.persistence

import androidx.room.*
import se.miun.frba1901.dt031g.chattingapp.logic.friend.Friendship

@Dao
interface FriendshipDAO {
    @Query("SELECT * FROM friendship WHERE from_user=:from and to_user=:to")
    fun getFriendship(from: Int, to: Int): Friendship

    @Query("SELECT * FROM friendship WHERE from_user=:userID")
    fun getAllUserFriendships(userID: Int): List<Friendship>

    @Query("UPDATE friendship SET pending=:pending WHERE from_user=:from and to_user=:to")
    fun setPending(from: Int, to: Int, pending: Boolean): Int

    @Insert
    fun insertAll(vararg friendships: Friendship)

    @Delete
    fun delete(friendshipID: Int)
}