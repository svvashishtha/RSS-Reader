package com.rssreader.ui.channels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rssreader.data.Channel
import com.rssreader.data.ChannelDao
import com.rssreader.util.SuggestedChannelProvider
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(private val channelDao: ChannelDao) :
    MyChannelRepository {
    override fun getSuggestedChannels(): LiveData<List<Channel>> {

        val liveDataList = MutableLiveData<List<Channel>>()
        liveDataList.value = SuggestedChannelProvider.getSuggestedChannels()
        return liveDataList
    }

}