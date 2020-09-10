package com.rssreader.ui.channels

import androidx.lifecycle.LiveData
import com.rssreader.data.Channel

interface MyChannelRepository {
    fun getSuggestedChannels(): LiveData<List<Channel>>
}