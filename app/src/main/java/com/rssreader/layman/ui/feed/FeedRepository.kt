package com.rssreader.layman.ui.feed

import com.rssreader.layman.data.Channel
import com.rssreader.layman.data.FeedWrapper
import com.rssreader.layman.network.ApiResponse

interface FeedRepository {
    suspend fun fetchRssFeedForChannel(channel: Channel): ApiResponse<FeedWrapper>
}