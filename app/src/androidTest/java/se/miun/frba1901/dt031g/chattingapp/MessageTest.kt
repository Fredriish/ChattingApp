package se.miun.frba1901.dt031g.chattingapp

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import se.miun.frba1901.dt031g.chattingapp.logic.message.Message
import se.miun.frba1901.dt031g.chattingapp.logic.user.User
import se.miun.frba1901.dt031g.chattingapp.persistence.DatabaseWithDAO
import se.miun.frba1901.dt031g.chattingapp.persistence.MessageDAO
import se.miun.frba1901.dt031g.chattingapp.persistence.UserDAO

@RunWith(AndroidJUnit4::class)
class MessageTest {
    private lateinit var db: DatabaseWithDAO
    private lateinit var dao: MessageDAO
    private lateinit var userDao: UserDAO
    private var user1: User = User("Fredrik")
    private var user2: User = User("Anna")
    private var user3: User = User("Stefan")
    private var user4: User = User("Henrik")
    private var user5: User = User("Alex")
    @Before
    fun initialDB(){
        try {
            db = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext<Context>(),
                DatabaseWithDAO::class.java
            ).build()
            dao = db.messageDAO()
            userDao = db.userDAO()
        }
        catch(e: Exception){
            System.err.println("Exception when creating a test database, printing stacktrace:")
            System.err.println(e.printStackTrace())
            throw e;
        }

        createAndSaveUsers(user1.name, user2.name, user3.name, user4.name, user5.name)
        user1 = userDao.getUsersByName(user1.name)[0]
        user2 = userDao.getUsersByName(user2.name)[0]
        user3 = userDao.getUsersByName(user3.name)[0]
        user4 = userDao.getUsersByName(user4.name)[0]
        user5 = userDao.getUsersByName(user5.name)[0]


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
    fun messagesExist(){

        var users: Array<User> = arrayOf(user1, user2, user3, user4, user5);
        var messages: Array<Message> = arrayOf(
            Message(
                user1.userID,
                user4.userID,
                "Message from " + user1.name + " to " + user4.name
            ),
            Message(
                user1.userID,
                user5.userID,
                "Message from " + user1.name + " to " + user5.name
            ),
            Message(
                user4.userID,
                user1.userID,
                "Message from " + user4.name + " to " + user1.name
            ),
            Message(
                user4.userID,
                user3.userID,
                "Message from " + user4.name + " to " + user3.name
            )
        )

        dao.insertAll(
                *messages
        )

        for(xUser: User in users){
            for(yUser: User in users){
                for(localMessage in messages){
                    if(localMessage.from == xUser.userID && localMessage.to == yUser.userID) {
                        var remoteMessages = dao.getMessagesFromUserToUser(xUser.userID, yUser.userID)
                        assert(remoteMessages.contains(localMessage))
                    }
                }
            }
        }

    }
    private fun createAndSaveUsers(vararg names: String){
        val nameIterator = names.iterator()
        while(nameIterator.hasNext()){
            val user = User(nameIterator.next())
            userDao.insertAll(user)
        }
    }
}