package se.miun.frba1901.dt031g.chattingapp.logic.user

import android.content.Context
import se.miun.frba1901.dt031g.chattingapp.persistence.AppDatabase

class UserManagerRoomImpl(context: Context) : IUserManager {
    var appContext: Context
    init{
        appContext = context
    }
    override suspend fun getUser(userID: Int): User? {
        return AppDatabase.getInstance(appContext).userDAO().getUserByID(userID)
    }

    override suspend fun tryToCreateUser(name: String): Boolean {
        AppDatabase.getInstance(appContext).userDAO().insertAll(User(name))
        return true
    }
}