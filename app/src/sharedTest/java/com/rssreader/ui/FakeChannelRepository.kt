package com.rssreader.ui

import com.rssreader.layman.data.Channel
import com.rssreader.layman.network.ApiResponse
import com.rssreader.layman.ui.channels.MyChannelRepository
import com.rssreader.layman.util.SuggestedChannelProvider
import javax.inject.Inject

class FakeChannelRepository @Inject constructor() : MyChannelRepository {
    override suspend fun getSuggestedChannels(): ApiResponse<List<Channel>> {
        return ApiResponse.success(SuggestedChannelProvider.getSuggestedChannels())
    }
}