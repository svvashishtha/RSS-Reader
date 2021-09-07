package com.rssreader.layman.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.decode.SvgDecoder
import com.rssreader.layman.R
import com.rssreader.layman.data.FeedItem
import com.rssreader.layman.data.FeedItemDiffCallback
import javax.inject.Inject

class RssFeedAdapter @Inject constructor(
    private val svgDecoder: SvgDecoder
) :
    ListAdapter<FeedItem, RssFeedItemViewHolder>(FeedItemDiffCallback) {
    var mListener: FeedItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssFeedItemViewHolder {
        return RssFeedItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.feed_item_layout, parent, false),
            svgDecoder
        )
    }

    override fun onBindViewHolder(holder: RssFeedItemViewHolder, position: Int) {
        holder.bind(getItem(position), mListener)
    }
}