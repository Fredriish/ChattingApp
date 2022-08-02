package se.miun.frba1901.dt031g.chattingapp.logic.friend

interface IFriendManager {
    suspend fun getFriendships(userID: Int) : Collection<Friendship>
    suspend fun requestFriendship(from: Int, to: Int): Friendship
    suspend fun removeFriendship(friendshipID: Int): Boolean
}