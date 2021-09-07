package com.rssreader.layman.ui.feed

import android.view.View
import com.rssreader.layman.data.FeedItem

interface FeedItemClickListener {
    fun openItem(item: FeedItem, itemView: View)
}