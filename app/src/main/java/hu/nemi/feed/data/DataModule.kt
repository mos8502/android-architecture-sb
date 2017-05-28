package hu.nemi.feed.data

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.content.Context

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideDatabase(context : Context) : ConferenceDatabase = ConferenceDatabase.createPersistentDatabase(context)
}