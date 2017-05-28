package hu.nemi.feed

import dagger.Component
import hu.nemi.feed.data.DataModule
import hu.nemi.feed.network.NetworkModule
import hu.nemi.feed.ui.FeedViewModel
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, DataModule::class, NetworkModule::class))
@Singleton
interface AppComponent {
    fun inject(feedViewModel: FeedViewModel)
}