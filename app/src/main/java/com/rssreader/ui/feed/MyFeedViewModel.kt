package com.rssreader.ui.feed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rssreader.data.Channel

class MyFeedViewModel @ViewModelInject constructor(val feedRepository: FeedRepository) :
    ViewModel() {
    fun fetchChannelRssFeed(channel: Channel) {
        feedRepository.fetchRssFeedForChannel(channel)
    }

    val channel = MutableLiveData<Channel>()
}