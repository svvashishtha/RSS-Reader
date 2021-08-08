package com.rssreader.ui.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rssreader.data.Channel
import com.rssreader.data.FeedWrapper
import com.rssreader.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MyFeedViewModel @Inject constructor(val feedRepository: FeedRepository) :
    ViewModel() {
    fun fetchChannelRssFeed(channel: Channel) {
        viewModelScope.launch {
            try {
                feed.value = ApiResponse.loading(null)
                val result = feedRepository.fetchRssFeedForChannel(channel)
                feed.value = result
            } catch (e: Exception) {
                e.printStackTrace()
                feed.value = ApiResponse.error(null, message = e.message?:"Something went wrong")
            }
        }
    }

    val channel = MutableLiveData<Channel>()
    val feed = MutableLiveData<ApiResponse<FeedWrapper>>()
}