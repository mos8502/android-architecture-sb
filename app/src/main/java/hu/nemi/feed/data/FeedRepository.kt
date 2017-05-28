package hu.nemi.feed.data

import hu.nemi.feed.model.AddResult
import hu.nemi.feed.model.FeedItem
import io.reactivex.Flowable
import io.reactivex.Single

interface FeedRepository {
    fun feed() : Flowable<List<FeedItem>>
    fun add(url : String) : Single<AddResult>
    fun delete(feedItem: FeedItem)
}