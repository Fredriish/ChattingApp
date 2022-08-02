package se.miun.frba1901.dt031g.chattingapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import se.miun.frba1901.dt031g.chattingapp.logic.message.Message
import se.miun.frba1901.dt031g.chattingapp.logic.user.User
import se.miun.frba1901.dt031g.chattingapp.utilities.SingletonHolder

//Database(entities = [Message::class, User::class, ], version = 1)
abstract class AppDatabase : DatabaseWithDAO() {
    companion object{
        private var instance: AppDatabase? = null;
        fun getInstance(context: Context): AppDatabase{
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "chatting.db"
                    ).build()
                }
                return instance!!
            }
        }
    }
}
