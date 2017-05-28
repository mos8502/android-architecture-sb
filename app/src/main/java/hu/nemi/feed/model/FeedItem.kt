package hu.nemi.feed.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "feed")
data class FeedItem(@PrimaryKey val url : String, val title: String, val description: String, val imageUrl: String, val added : Long = System.currentTimeMillis())