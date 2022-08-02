package se.miun.frba1901.dt031g.chattingapp.logic.message

interface IMessageManager {
    suspend fun getMessages(from: Int, to: Int, messageAmount: Int = 0): Collection<Message>
    suspend fun sendMessage(message: Message): Boolean
    suspend fun removeMessage(message: Message)
}