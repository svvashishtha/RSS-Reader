package com.rssreader.data

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FeedWrapper(
    @SerializedName("title") @Expose val title: String?,
    @SerializedName("description") @Expose val description: String?,
    @SerializedName("item") @Expose val itemList: List<FeedItem>
    )

