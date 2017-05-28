package hu.nemi.feed.network

import retrofit2.Retrofit

class LinkPreviewRestClient(retrofitBuilder : Retrofit.Builder) {
    private val linkPreviewApiInterface : LinkPreviewApiInterface = retrofitBuilder
            .build()
            .create(LinkPreviewApiInterface::class.java)

    fun get(url : String) = linkPreviewApiInterface.get(url)
}