package hu.nemi.feed

import dagger.Module
import android.content.Context
import dagger.Provides
import hu.nemi.feed.data.ConferenceDatabase
import hu.nemi.feed.data.DefaultFeedRepository
import hu.nemi.feed.data.FeedRepository
import hu.nemi.feed.network.LinkPreviewRestClient
import javax.inject.Singleton

@Module
class AppModule(val context : Context) {
    @Singleton
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideStreamRepository(database: ConferenceDatabase, linkPreviewRestClient: LinkPreviewRestClient) : FeedRepository
            = DefaultFeedRepository(database.streams(), linkPreviewRestClient)
}