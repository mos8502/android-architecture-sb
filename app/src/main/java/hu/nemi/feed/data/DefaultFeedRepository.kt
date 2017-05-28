package hu.nemi.feed.data

import hu.nemi.feed.cardDescription
import hu.nemi.feed.cardImage
import hu.nemi.feed.cardTitle
import hu.nemi.feed.map
import hu.nemi.feed.model.AddResult
import hu.nemi.feed.model.FeedItem
import hu.nemi.feed.network.LinkPreviewRestClient
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.SingleSubject
import org.jsoup.Jsoup

class DefaultFeedRepository(val feedDao: FeedDao, val restClient: LinkPreviewRestClient) : FeedRepository {

    override fun feed() = feedDao.feed()

    override fun add(url: String): SingleSubject<AddResult> {
        val result = SingleSubject.create<AddResult>()
        feedDao.count(url)
                .firstOrError()
                .flatMap {
                    if (it > 0) Single.just(AddResult.Duplicate())
                    else
                        restClient.get(url)
                                .map {
                                    val newFeedItem = Jsoup.parse(it)
                                            .map {
                                                FeedItem(url = url,
                                                        title = cardTitle,
                                                        description = cardDescription,
                                                        imageUrl = cardImage)
                                            }
                                    feedDao.add(newFeedItem)
                                    AddResult.Success(newFeedItem)
                                }
                }
                .onErrorReturn { AddResult.Error(it) }
                .subscribeOn(Schedulers.io())
                .subscribe(result)

        return result
    }

    override fun delete(feedItem: FeedItem) {
        Completable.fromAction { feedDao.remove(feedItem) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }
}