package com.rssreader.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rssreader.data.Channel
import com.rssreader.network.ApiResponse
import com.rssreader.ui.channels.MyChannelRepository
import com.rssreader.util.SuggestedChannelProvider
import javax.inject.Inject

class FakeChannelRepository @Inject constructor() : MyChannelRepository {
    override suspend fun getSuggestedChannels(): ApiResponse<List<Channel>> {
        return ApiResponse.success(SuggestedChannelProvider.getSuggestedChannels())
    }
}