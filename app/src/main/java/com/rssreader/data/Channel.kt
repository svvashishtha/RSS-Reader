package com.rssreader.data

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Channel(
    val title: String? = null,
    val description: String? = null,
    val language: String? = null,
    val webMaster: String? = null,
    val docs: String? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    val image: Image? = null,
    val channelUrl: String = "",
    val lastBuildDate: String? = null,
    val ttl: Int? = null
) : Serializable

object ChannelDiffCallback : DiffUtil.ItemCallback<Channel>() {
    override fun areItemsTheSame(oldItem: Channel, newItem: Channel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Channel, newItem: Channel): Boolean {
        return oldItem.channelUrl == newItem.channelUrl
    }

}