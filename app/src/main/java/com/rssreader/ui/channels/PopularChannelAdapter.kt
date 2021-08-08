package com.rssreader.ui.channels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import coil.load
import com.rssreader.R
import com.rssreader.data.Channel
import com.rssreader.data.ChannelDiffCallback
import javax.inject.Inject

class PopularChannelAdapter @Inject constructor(private val svgDecoder: SvgDecoder) :
    ListAdapter<Channel, PopularChannelViewHolder>(ChannelDiffCallback) {

    var channelAdapterListener: ChannelAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularChannelViewHolder {
        return PopularChannelViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.popular_channel_item_layout, parent, false), svgDecoder
        )
    }

    override fun onBindViewHolder(holder: PopularChannelViewHolder, position: Int) {
        holder.bindView(getItem(position), channelAdapterListener)
    }


    interface ChannelAdapterListener {
        fun channelClicked(cardView: View,channel: Channel)
    }
}

class PopularChannelViewHolder(itemView: View, private val svgDecoder: SvgDecoder) :
    RecyclerView.ViewHolder(itemView) {
    private val channelImage = itemView.findViewById<ImageView>(R.id.channel_image)
    private val channelName = itemView.findViewById<TextView>(R.id.channel_name)
    fun bindView(
        channel: Channel,
        channelAdapterListener: PopularChannelAdapter.ChannelAdapterListener?
    ) {
        channelName.text = channel.title
        if (channel.image != null) {
            if (channel.image.endsWith("svg")) {
                channelImage.load(channel.image) {
                    decoder(svgDecoder)
                }
            } else {
                channelImage.load(channel.image)
            }
        }
        itemView.setOnClickListener {
            channelAdapterListener?.channelClicked(it.findViewById(R.id.channel_card), channel)
        }
    }
}