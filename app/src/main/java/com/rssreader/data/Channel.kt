package com.rssreader.data

data class Channel(
    val title: String?,
    val description: String?,
    val language: String?,
    val webMaster: String?,
    val docs: String?,
    val id: Int,
    val items: List<FeedItem>?,
    val image: Image?,
    val channelUrl: String,
    val lastBuildDate : String?,
    val ttl : Int?
)