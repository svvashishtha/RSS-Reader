package com.rssreader.layman.ui.channels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rssreader.layman.data.Channel
import com.rssreader.layman.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyChannelsViewModel @Inject constructor(val myChannelRepository: MyChannelRepository) :
    ViewModel() {

    var suggestedChannelsApiResponse = MutableLiveData<ApiResponse<List<Channel>>>()


    fun getChannels() {
        suggestedChannelsApiResponse.value = ApiResponse.loading(null)
        viewModelScope.launch {
            suggestedChannelsApiResponse.value = myChannelRepository.getSuggestedChannels()
        }
    }
}