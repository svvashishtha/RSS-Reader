package com.rssreader.ui.feed

import com.rssreader.data.Channel
import com.rssreader.data.FeedWrapper
import com.rssreader.network.ApiResponse

interface FeedRepository {
    suspend fun fetchRssFeedForChannel(channel: Channel): ApiResponse<FeedWrapper>
}