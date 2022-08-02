package se.miun.frba1901.dt031g.chattingapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import se.miun.frba1901.dt031g.chattingapp.logic.user.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE userID=:userID")
    fun getUserByID(userID: Int): User?

    @Query("SELECT * FROM user WHERE name=:name")
    fun getUsersByName(name: String): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}