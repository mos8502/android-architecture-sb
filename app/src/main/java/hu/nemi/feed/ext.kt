package hu.nemi.feed

import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jsoup.nodes.Document
import java.util.*

fun ViewGroup.inflate(@IdRes layout: Int, attach: Boolean = false) = LayoutInflater.from(this.context).inflate(layout, this, attach)

fun <T, R> T.map(mapper : T.() -> R) = mapper.invoke(this)

fun <T : View> RecyclerView.ViewHolder.findViewById(@IdRes viewId: Int) = itemView.findViewById(viewId) as T

fun Document.getProperty(property: String) = select("meta[property=$property]")?.attr("content")

fun Document.getMeta(name : String) = select("meta[name=$name]")?.attr("content")

fun Document.getLink(rel : String) = select("link[rel=$rel")?.attr("href")

val Document.cardTitle : String
    get() = getProperty("og:title") ?: ""

val Document.cardDescription : String
        get() = getProperty("og:description") ?: ""

val Document.cardImage : String
    get() = getProperty("og:image") ?: ""