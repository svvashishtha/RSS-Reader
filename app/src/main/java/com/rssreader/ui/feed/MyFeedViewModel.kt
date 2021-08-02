package com.rssreader.ui.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rssreader.data.Channel
import com.rssreader.data.FeedWrapper
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
                val result = feedRepository.fetchRssFeedForChannel(channel)
                feed.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    val channel = MutableLiveData<Channel>()
    val feed = MutableLiveData<FeedWrapper>()
}