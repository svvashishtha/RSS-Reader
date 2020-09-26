package com.rssreader.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rssreader.data.Channel
import com.rssreader.ui.channels.MyChannelRepository
import com.rssreader.util.SuggestedChannelProvider
import javax.inject.Inject

class FakeChannelRepository @Inject constructor() : MyChannelRepository {
    override fun getSuggestedChannels(): LiveData<List<Channel>> {
        val liveDataList = MutableLiveData<List<Channel>>()
        liveDataList.postValue( SuggestedChannelProvider.getSuggestedChannels())
        return liveDataList
    }
}