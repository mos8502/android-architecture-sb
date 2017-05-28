package hu.nemi.feed

import android.app.Application

class FeedApp : Application() {
    val appComponent : AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
}