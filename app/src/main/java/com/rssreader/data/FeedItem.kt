package com.rssreader.data

data class FeedItem(
    val title: String?,
    val link: String?,
    val description: String?,
    val enclosure: Enclosure?,
    val guid: Guid?,
    val pubDate: String?,
    val source: Source?,
)