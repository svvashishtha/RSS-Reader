package com.rssreader.data

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FeedWrapper(
    @SerializedName("title") @Expose val title: String?,
    @SerializedName("description") @Expose val description: String?,
    @SerializedName("item") @Expose val itemList: List<FeedItem>
    )

data class FeedItem(@SerializedName("title") @Expose val title: String?,
                    @SerializedName("description") @Expose val description: String?,
                    @SerializedName("link") @Expose val link: String?,
                    @SerializedName("pubDate") @Expose val publicationDate: String?,
                    @SerializedName("media") @Expose val media: Media?)

data class Media(@SerializedName("href") @Expose val href: String?,
                 @SerializedName("type") @Expose val type: String?
){
    override fun toString(): String {
        return Gson().toJson(this)
    }
}
object FeedItemDiffCallback : DiffUtil.ItemCallback<FeedItem>() {
    override fun areItemsTheSame(oldItem: FeedItem, newItem: FeedItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: FeedItem, newItem: FeedItem): Boolean {
        return oldItem.publicationDate == newItem.publicationDate
    }

}