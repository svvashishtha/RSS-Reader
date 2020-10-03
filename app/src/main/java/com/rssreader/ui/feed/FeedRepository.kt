package com.rssreader.ui.feed

import com.rssreader.data.Channel

interface FeedRepository {
    fun fetchRssFeedForChannel(channel: Channel)
}