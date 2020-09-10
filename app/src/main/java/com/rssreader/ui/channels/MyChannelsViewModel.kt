package com.rssreader.ui.channels

import androidx.lifecycle.ViewModel

class MyChannelsViewModel(private val myChannelRepository: MyChannelRepository) : ViewModel() {

    val suggestedChannels = myChannelRepository.getSuggestedChannels()

}