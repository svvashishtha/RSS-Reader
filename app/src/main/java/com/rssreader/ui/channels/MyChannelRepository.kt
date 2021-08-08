package com.rssreader.ui.channels

import androidx.lifecycle.LiveData
import com.rssreader.data.Channel
import com.rssreader.network.ApiResponse

interface MyChannelRepository {
    suspend fun getSuggestedChannels(): ApiResponse<List<Channel>>
}