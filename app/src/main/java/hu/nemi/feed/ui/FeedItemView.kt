package hu.nemi.feed.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import hu.nemi.feed.model.AdapterUpdate
import hu.nemi.feed.model.FeedItem

class FeedItemView(context: Context, attrs: AttributeSet?, defStyle: Int) : RecyclerView(context, attrs, defStyle) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        adapter = BaseAdapter({ parent, _ -> FeedItemViewHolder(parent) })
        layoutManager = LinearLayoutManager(context)
    }

    fun bind(update: AdapterUpdate<FeedItem>) {
        update.apply(adapter as BaseAdapter<FeedItem>)
    }

    operator fun get(adapterPosition: Int) = (adapter as BaseAdapter<FeedItem>)[adapterPosition]
}