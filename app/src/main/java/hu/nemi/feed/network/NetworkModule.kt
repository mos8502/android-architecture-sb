package hu.nemi.feed.network

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitBuilder() = Retrofit.Builder()
            .baseUrl("https://google.com")
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())

    @Singleton
    @Provides
    fun provideLinkPreviewRestClient(retrofitBuilder : Retrofit.Builder, context : Context) : LinkPreviewRestClient = LinkPreviewRestClient(retrofitBuilder)
}