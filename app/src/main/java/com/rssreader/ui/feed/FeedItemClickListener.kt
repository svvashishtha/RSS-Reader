package com.rssreader.ui.feed

import android.view.View
import com.rssreader.data.FeedItem

interface FeedItemClickListener {
    fun openItem(item: FeedItem, itemView: View)
}