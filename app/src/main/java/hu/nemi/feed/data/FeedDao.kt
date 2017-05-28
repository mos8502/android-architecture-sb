package hu.nemi.feed.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.Query
import hu.nemi.feed.model.FeedItem
import io.reactivex.Flowable

@Dao
abstract class FeedDao {

    @Query("SELECT * FROM feed ORDER BY added DESC")
    abstract fun feed() : Flowable<List<FeedItem>>

    @Query("SELECT count(*) FROM feed WHERE url=:p0")
    abstract fun count(url : String) : Flowable<Int>


    @Insert(onConflict = IGNORE)
    abstract fun add(feedItem: FeedItem)

    @Delete
    abstract fun remove(feedItem: FeedItem)
}