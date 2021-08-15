package com.rssreader.ui.channels

import android.view.View
import com.rssreader.data.Channel

interface ChannelAdapterListener {
    fun channelClicked(cardView: View, channel: Channel)
}