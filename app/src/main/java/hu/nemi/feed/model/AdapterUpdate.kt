package hu.nemi.feed.model

import android.support.v7.util.DiffUtil
import hu.nemi.feed.ui.BaseAdapter

data class AdapterUpdate<M>(val data: List<M>, private val diff: DiffUtil.DiffResult?) {
    companion object {
        fun <M> empty() = AdapterUpdate<M>(emptyList(), null)
    }

    fun apply(adapter: BaseAdapter<M>) {
        adapter.data = data
        diff?.dispatchUpdatesTo(adapter) ?: adapter.notifyDataSetChanged()
    }
}