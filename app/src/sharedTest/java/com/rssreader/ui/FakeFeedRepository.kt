package com.rssreader.ui

import com.rssreader.data.Channel
import com.rssreader.data.FeedWrapper
import com.rssreader.network.ApiResponse
import com.rssreader.ui.feed.FeedRepository
import javax.inject.Inject

class FakeFeedRepository @Inject constructor() : FeedRepository {
    override suspend fun fetchRssFeedForChannel(channel: Channel): ApiResponse<FeedWrapper> {
        return ApiResponse.success(FeedWrapper("Mock title","mock description", mutableListOf()))
    }
}