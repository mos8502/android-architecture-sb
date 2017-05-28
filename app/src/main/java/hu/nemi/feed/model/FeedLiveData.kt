package hu.nemi.feed.model

import android.arch.lifecycle.LiveData
import android.support.v7.util.DiffUtil
import hu.nemi.feed.data.FeedRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FeedLiveData(private val repository: FeedRepository) : LiveData<AdapterUpdate<FeedItem>>() {
    private var streamDisposable: Disposable? = null
    
    override fun onActive() {
        onInactive()

        streamDisposable = repository.feed()
                .scan(AdapterUpdate.empty<FeedItem>()) { old, new ->
                    AdapterUpdate(new, DiffUtil.calculateDiff(diff(old.data, new), true))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { value = it }
    }

    override fun onInactive() {
        streamDisposable?.dispose()
    }

    private fun diff(oldData: List<FeedItem>, newData: List<FeedItem>) = object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean = oldData[oldPosition].url == newData[newPosition].url

        override fun getOldListSize(): Int = oldData.size

        override fun getNewListSize(): Int = newData.size

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean = oldData[oldPosition] == newData[newPosition]
    }
}