package hu.nemi.feed.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class BaseAdapter<M>(private val viewHolderFactory: (ViewGroup, Int) -> BindableViewHolder<M>, private val viewTypeMapper: (M) -> Int = { 1 }) : RecyclerView.Adapter<BindableViewHolder<M>>() {
    var data: List<M> = emptyList()

    override fun onBindViewHolder(viewHolder: BindableViewHolder<M>, position: Int) {
        viewHolder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = viewHolderFactory.invoke(parent, viewType)

    override fun getItemViewType(position: Int) = viewTypeMapper.invoke(data[position])

    override fun getItemCount(): Int = data.size

    operator fun get(position: Int) = data[position]
}