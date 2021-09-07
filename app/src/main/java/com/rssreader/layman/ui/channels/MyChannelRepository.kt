package com.rssreader.layman.ui.channels

import com.rssreader.layman.data.Channel
import com.rssreader.layman.network.ApiResponse

interface MyChannelRepository {
    suspend fun getSuggestedChannels(): ApiResponse<List<Channel>>
}