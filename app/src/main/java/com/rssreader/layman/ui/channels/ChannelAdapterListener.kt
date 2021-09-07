package com.rssreader.layman.ui.channels

import android.view.View
import com.rssreader.layman.data.Channel

interface ChannelAdapterListener {
    fun channelClicked(cardView: View, channel: Channel)
}