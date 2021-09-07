package com.rssreader.layman.data

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FeedItem(@SerializedName("title") @Expose val title: String?,
                    @SerializedName("description") @Expose val description: String?,
                    @SerializedName("link") @Expose val link: String?,
                    @SerializedName("pubDate") @Expose val publicationDate: String?,
                    @SerializedName("media") @Expose val media: Media?): Serializable



object FeedItemDiffCallback : DiffUtil.ItemCallback<FeedItem>() {
    override fun areItemsTheSame(oldItem: FeedItem, newItem: FeedItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: FeedItem, newItem: FeedItem): Boolean {
        return oldItem.publicationDate == newItem.publicationDate
    }

}