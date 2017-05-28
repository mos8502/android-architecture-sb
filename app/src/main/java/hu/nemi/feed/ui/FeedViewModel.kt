package hu.nemi.feed.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import hu.nemi.feed.FeedApp
import hu.nemi.feed.data.FeedRepository
import hu.nemi.feed.model.FeedItem
import hu.nemi.feed.model.FeedLiveData
import javax.inject.Inject

class FeedViewModel(app : Application) : AndroidViewModel(app) {
    @Inject lateinit var feedRepository: FeedRepository

    init {
        (app as FeedApp).appComponent.inject(this)
    }

    fun stream() = FeedLiveData(feedRepository)

    fun addStreamItem(url : String) {
        feedRepository.add(url)
    }

    fun delete(feedItem: FeedItem) {
        feedRepository.delete(feedItem)
    }
}