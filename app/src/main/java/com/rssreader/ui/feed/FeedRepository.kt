package com.rssreader.ui.feed

import com.rssreader.data.Channel
import com.rssreader.data.FeedWrapper

interface FeedRepository {
    suspend fun fetchRssFeedForChannel(channel: Channel): FeedWrapper
}