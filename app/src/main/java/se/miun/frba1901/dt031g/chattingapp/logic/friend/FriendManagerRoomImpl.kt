package se.miun.frba1901.dt031g.chattingapp.logic.friend

import android.content.Context
import se.miun.frba1901.dt031g.chattingapp.persistence.AppDatabase
import java.util.*

class FriendManagerRoomImpl(context: Context) : IFriendManager{
    private var appContext: Context

    init{
        appContext = context
    }
    override suspend fun getFriendships(userID: Int): Collection<Friendship> {
        return AppDatabase.getInstance(appContext).friendshipDAO().getAllUserFriendships(userID)
    }

    override suspend fun requestFriendship(from: Int, to: Int): Friendship {
        val friendship = Friendship(from, to, Date())
        AppDatabase.getInstance(appContext).friendshipDAO().insertAll(friendship)
        return friendship
    }

    override suspend fun removeFriendship(friendshipID: Int): Boolean {
        AppDatabase.getInstance(appContext).friendshipDAO().delete(friendshipID)
        return true
    }
}