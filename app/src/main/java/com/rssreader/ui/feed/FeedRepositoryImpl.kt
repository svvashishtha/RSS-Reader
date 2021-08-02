package com.rssreader.ui.feed

import com.rssreader.data.Channel
import com.rssreader.data.FeedWrapper
import com.rssreader.network.RSSService
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(val rssService: RSSService): FeedRepository {
    override suspend fun fetchRssFeedForChannel(channel: Channel): FeedWrapper {
       return rssService.getRssService(channel.channelUrl +"/").getFeedAsync()
    }
}