package com.rssreader.util

import com.rssreader.data.Channel

class SuggestedChannelProvider {
    companion object {
        fun getSuggestedChannels(): MutableList<Channel> {
            val suggestedChannelList = mutableListOf<Channel>()
            suggestedChannelList.add(
                Channel(
                    title = "NASA Image of the Day",
                    description = "The latest NASA \\\"Image of the Day\\\" image.",
                    language = "en-us",
                    webMaster = null,
                    docs = null,
                    id = "1",
                    channelUrl = "http://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss",
                    image = "https://www.nasa.gov/sites/all/themes/custom/nasatwo/images/nasa-logo.svg",
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
                    id = "2",
                    channelUrl = "http://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss",
                    image = "https://news.bbcimg.co.uk/nol/shared/img/bbc_news_120x60.gif",
                    ttl = null,
                    lastBuildDate = null
                )
            )
            return suggestedChannelList
        }
    }
}