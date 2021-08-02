package com.rssreader.data

import androidx.recyclerview.widget.DiffUtil
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
@Root(name = "item", strict = false)
class FeedItem constructor(
    @field: Element(name = "title", required = false)
    val title: String?,
    @field: Element(name = "link", required = false)
    val link: String?,
    @field: Element(name = "description", required = false)
    val description: String?,
    /*@field: Element(name = "enclosure", required = false)
    val enclosure: Enclosure?,*/
    @field: Element(name = "guid", required = false)
    val guid: Guid?,
    @field: Element(name = "pubdate", required = false)
    val pubDate: String?,
    @field: Element(name = "source", required = false)
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