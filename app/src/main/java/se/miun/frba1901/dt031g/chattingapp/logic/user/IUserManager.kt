package se.miun.frba1901.dt031g.chattingapp.logic.user

interface IUserManager {
    suspend fun getUser(userID: Int): User?
    suspend fun tryToCreateUser(name: String): Boolean
}