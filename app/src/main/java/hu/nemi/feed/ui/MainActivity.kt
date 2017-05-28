package hu.nemi.feed.ui

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.*
import hu.nemi.feed.R
import hu.nemi.feed.model.AdapterUpdate
import hu.nemi.feed.model.FeedItem

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner, Observer<AdapterUpdate<FeedItem>> {
    private val streamViewModel by lazy { ViewModelProviders.of(this).get(FeedViewModel::class.java) }
    private val feedItemView: FeedItemView by lazy { findViewById(R.id.stream_view) as FeedItemView }
    private val lifecycleRegistry = LifecycleRegistry(this)
    private val itemTouchHelper = ItemTouchHelper(object : SimpleCallback(0, LEFT or RIGHT) {
        override fun onMove(p0: RecyclerView?, p1: RecyclerView.ViewHolder?, p2: RecyclerView.ViewHolder?): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            streamViewModel.delete(feedItemView[viewHolder.adapterPosition])
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemTouchHelper.attachToRecyclerView(feedItemView)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        streamViewModel.stream().observe(this, this)

        if (Intent.ACTION_SEND == intent.action && savedInstanceState == null) {
            streamViewModel.addStreamItem(intent.getStringExtra(Intent.EXTRA_TEXT))
        }
    }

    override fun getLifecycle() = lifecycleRegistry

    override fun onChanged(update: AdapterUpdate<FeedItem>?) {
        update?.let {
            feedItemView.bind(it)
        }
    }
}
