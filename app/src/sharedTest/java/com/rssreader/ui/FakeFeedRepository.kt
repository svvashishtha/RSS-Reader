package com.rssreader.ui

import com.rssreader.data.Channel
import com.rssreader.ui.feed.FeedRepository
import javax.inject.Inject

class FakeFeedRepository @Inject constructor() : FeedRepository {
    override fun fetchRssFeedForChannel(channel: Channel) {

    }
}