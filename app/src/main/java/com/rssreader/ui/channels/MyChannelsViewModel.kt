package com.rssreader.ui.channels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MyChannelsViewModel @Inject constructor(myChannelRepository: MyChannelRepository) : ViewModel() {

    val suggestedChannels = myChannelRepository.getSuggestedChannels()

}