package hu.nemi.feed.ui

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import hu.nemi.feed.inflate

abstract class BindableViewHolder<M>(parent : ViewGroup, @LayoutRes layout : Int) : RecyclerView.ViewHolder(parent.inflate(layout, false)) {
    abstract fun bind(model : M)
}