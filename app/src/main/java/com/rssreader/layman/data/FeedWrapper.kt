package com.rssreader.layman.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FeedWrapper(
    @SerializedName("title") @Expose val title: String?,
    @SerializedName("description") @Expose val description: String?,
    @SerializedName("item") @Expose val itemList: List<FeedItem>
    )

