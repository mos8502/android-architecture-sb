package hu.nemi.feed.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import hu.nemi.feed.model.FeedItem

@Database(
        entities = arrayOf(FeedItem::class),
        version = 1
)
abstract class ConferenceDatabase : RoomDatabase() {
    abstract fun streams() : FeedDao

    companion object {
        private val DB_NAME = "conference"
        fun createPersistentDatabase(context: Context): ConferenceDatabase
                = Room.databaseBuilder(context.applicationContext, ConferenceDatabase::class.java, DB_NAME).build()
    }
}