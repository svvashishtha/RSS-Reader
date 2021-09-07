package com.rssreader.layman.ui.channels

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.decode.SvgDecoder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.rssreader.layman.R
import com.rssreader.layman.data.Channel

class PopularChannelFireStoreAdapter(val options: FirestoreRecyclerOptions<Channel>, val svgDecoder: SvgDecoder) :
    FirestoreRecyclerAdapter<Channel, PopularChannelViewHolder>(options) {
    var channelAdapterListener: ChannelAdapterListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularChannelViewHolder {
        return PopularChannelViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.popular_channel_item_layout, parent, false), svgDecoder
        )
    }

    override fun onBindViewHolder(holder: PopularChannelViewHolder, position: Int, model: Channel) {
        holder.bindView(channel = model, channelAdapterListener)
    }
}