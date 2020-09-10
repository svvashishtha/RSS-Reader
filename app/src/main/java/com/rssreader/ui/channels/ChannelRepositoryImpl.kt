package com.rssreader.ui.channels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rssreader.data.Channel
import com.rssreader.data.Image

class ChannelRepositoryImpl : MyChannelRepository {
    override fun getSuggestedChannels(): LiveData<List<Channel>> {
        val suggestedChannelList = mutableListOf<Channel>()
        suggestedChannelList.add(
            Channel(
                title = "NASA Image of the Day",
                description = "The latest NASA \\\"Image of the Day\\\" image.",
                language = "en-us",
                webMaster = null,
                docs = null,
                id = 0,
                channelUrl = "http://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss",
                items = null,
                image = null,
                lastBuildDate = null,
                ttl = null
            )
        )
        suggestedChannelList.add(
            Channel(
                title = "BBC News - Home",
                description = "BBC News - Home",
                language = "en-gb",
                webMaster = null,
                docs = null,
                id = 0,
                channelUrl = "http://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss",
                items = null,
                image = Image(url = "https://news.bbcimg.co.uk/nol/shared/img/bbc_news_120x60.gif",
                title = "BBC News - Home",
                link = "https://www.bbc.co.uk/news/"),
                ttl = null,
                lastBuildDate = null
            )
        )
        val liveDataList = MutableLiveData<List<Channel>>()
        liveDataList.value = suggestedChannelList
        return liveDataList
    }

}