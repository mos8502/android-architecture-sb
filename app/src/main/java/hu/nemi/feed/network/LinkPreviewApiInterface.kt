package hu.nemi.feed.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface LinkPreviewApiInterface {
    @GET
    fun get(@Url url : String) : Single<String>
}