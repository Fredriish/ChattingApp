package se.miun.frba1901.dt031g.chattingapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import se.miun.frba1901.dt031g.chattingapp.logic.user.User
import se.miun.frba1901.dt031g.chattingapp.persistence.DatabaseWithDAO
import se.miun.frba1901.dt031g.chattingapp.persistence.MessageDAO
import se.miun.frba1901.dt031g.chattingapp.persistence.UserDAO

@RunWith(AndroidJUnit4::class)
class UserTest {
    private lateinit var db: DatabaseWithDAO
    private lateinit var dao: UserDAO

    @Before
    fun createDB(){
        try {
            db = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext<Context>(),
                DatabaseWithDAO::class.java
            ).build()
            dao = db.userDAO()
        }
        catch(e: Exception){
            System.err.println("Exception when creating a test database, printing stacktrace:")
            System.err.println(e.printStackTrace())
            throw e;
        }
    }
    @After
    fun closeDB(){
        try {
            db.close();
        }
        catch(e: Exception){
            System.err.println("Exception occured when closing the test database, printing stacktrace")
            System.err.println(e.printStackTrace())
            throw e
        }
    }
    @Test
    fun usersCreated(){

        val user1 = "Stefan"
        val user2 = "Fredrik"
        val user3 = "Anna"

        createAndSaveUsers(user1, user2, user3)
        var fetchedUser1: User? = null
        var fetchedUser2: User? = null
        var fetchedUser3: User? = null
        var users: List<User> = dao.getAllUsers()
        for(user in users){
            when(user.name){
                user1 -> fetchedUser1 = User(user1)
                user2 -> fetchedUser2 = User(user2)
                user3 -> fetchedUser3 = User(user3)
            }
        }
        assert(fetchedUser1 != null && fetchedUser2 != null && fetchedUser3 != null)
    }
    @Test
    fun userGeneratedID(){
        createAndSaveUsers("1", "2", "3")

        assert(dao.getAllUsers().distinctBy{ it.userID }.size == 3)
    }
    @Test
    fun userDeleted(){
        val name1 = "Anna"
        val name2 = "Fredrik"
        createAndSaveUsers(name1, name2)

        val foundName1 = dao.getUsersByName(name1)
        val foundName2 = dao.getUsersByName(name2)

        assert(!foundName1.isEmpty() && !foundName2.isEmpty())
        assert(foundName1[0].name == name1 && foundName2[0].name == name2)
        dao.delete(foundName1[0])
        assert(dao.getUserByID(foundName1[0].userID) == null)
    }
    private fun createAndSaveUsers(vararg names: String){
        val nameIterator = names.iterator()
        while(nameIterator.hasNext()){
            val user = User(nameIterator.next())
            dao.insertAll(user)
        }
    }
}