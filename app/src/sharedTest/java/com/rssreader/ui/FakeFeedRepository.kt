package com.rssreader.ui

import com.rssreader.layman.data.Channel
import com.rssreader.layman.data.FeedWrapper
import com.rssreader.layman.network.ApiResponse
import com.rssreader.layman.ui.feed.FeedRepository
import javax.inject.Inject

class FakeFeedRepository @Inject constructor() : FeedRepository {
    override suspend fun fetchRssFeedForChannel(channel: Channel): ApiResponse<FeedWrapper> {
        return ApiResponse.success(FeedWrapper("Mock title","mock description", mutableListOf()))
    }
}