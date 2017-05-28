package hu.nemi.feed.model

sealed class AddResult {
    data class Success(val feedItem : FeedItem) : AddResult()
    data class Error(val error : Throwable) : AddResult()
    class Duplicate() : AddResult()
}