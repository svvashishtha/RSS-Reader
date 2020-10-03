package com.rssreader.data

import androidx.recyclerview.widget.DiffUtil

data class FeedItem(
    val title: String?,
    val link: String?,
    val description: String?,
    val enclosure: Enclosure?,
    val guid: Guid?,
    val pubDate: String?,
    val source: Source?,
)

object FeedItemDiffCallback : DiffUtil.ItemCallback<FeedItem>() {
    override fun areItemsTheSame(oldItem: FeedItem, newItem: FeedItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: FeedItem, newItem: FeedItem): Boolean {
        return oldItem.title == newItem.title
                && oldItem.pubDate == newItem.pubDate
                && oldItem.source == newItem.source
    }

}