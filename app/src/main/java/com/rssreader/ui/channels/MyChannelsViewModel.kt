package com.rssreader.ui.channels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MyChannelsViewModel @ViewModelInject constructor(private val myChannelRepository: MyChannelRepository) : ViewModel() {

    val suggestedChannels = myChannelRepository.getSuggestedChannels()

}