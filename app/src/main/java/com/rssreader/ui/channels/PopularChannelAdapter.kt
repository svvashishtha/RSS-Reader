package com.rssreader.ui.channels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import coil.load
import com.rssreader.R
import com.rssreader.data.Channel
import javax.inject.Inject

class PopularChannelAdapter @Inject constructor(private val svgDecoder: SvgDecoder) :
    RecyclerView.Adapter<PopularChannelViewHolder>() {
    var channelList = ArrayList<Channel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularChannelViewHolder {
        return PopularChannelViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.popular_channel_item_layout, parent, false), svgDecoder
        )
    }

    override fun onBindViewHolder(holder: PopularChannelViewHolder, position: Int) {
        holder.bindView(channelList[position])
    }

    override fun getItemCount(): Int {
        return channelList.size
    }

    fun setData(channelList: ArrayList<Channel>) {
        this.channelList = channelList
    }
}

class PopularChannelViewHolder(itemView: View, private val svgDecoder: SvgDecoder) :
    RecyclerView.ViewHolder(itemView) {
    private val channelImage = itemView.findViewById<ImageView>(R.id.channel_image)
    private val channelName = itemView.findViewById<TextView>(R.id.channel_name)
    fun bindView(channel: Channel) {
        channelName.text = channel.title
        if (channel.image?.url != null) {
            if (channel.image.url.endsWith("svg")) {
                channelImage.load(channel.image.url) {
                    decoder(svgDecoder)
                }
            } else {
                channelImage.load(channel.image.url)
            }
        }
    }
}