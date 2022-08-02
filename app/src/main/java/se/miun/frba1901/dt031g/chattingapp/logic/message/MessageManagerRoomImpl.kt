package se.miun.frba1901.dt031g.chattingapp.logic.message

import android.content.Context
import se.miun.frba1901.dt031g.chattingapp.persistence.AppDatabase

class MessageManagerRoomImpl(context: Context): IMessageManager {
    private var appContext: Context
    init{
        appContext = context
    }
    override suspend fun getMessages(
        from: Int,
        to: Int,
        messageAmount: Int
    ): Collection<Message> {
        return AppDatabase.getInstance(appContext).messageDAO().getMessagesFromUserToUser(from, to) as Collection<Message>
    }

    override suspend fun sendMessage(message: Message): Boolean {
        AppDatabase.getInstance(appContext).messageDAO().insertAll(message);
        return true;
    }

    override suspend fun removeMessage(message: Message) {
        AppDatabase.getInstance(appContext).messageDAO().delete(message);
    }
}