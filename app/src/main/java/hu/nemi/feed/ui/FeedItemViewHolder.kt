package hu.nemi.feed.ui

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import hu.nemi.feed.R
import hu.nemi.feed.findViewById
import hu.nemi.feed.model.FeedItem

class FeedItemViewHolder(parent : ViewGroup) : BindableViewHolder<FeedItem>(parent, R.layout.view_feed_item) {
    private val title : TextView by lazy { findViewById<TextView>(R.id.stream_title) }
    private val description : TextView by lazy { findViewById<TextView>(R.id.stream_description) }
    private val image : ImageView by lazy { findViewById<ImageView>(R.id.stream_image)}

    override fun bind(model: FeedItem) {
        title.text = model.title
        description.text = model.description
        Glide.with(itemView.context)
                .load(model.imageUrl)
                .centerCrop()
                .into(image)
    }
}