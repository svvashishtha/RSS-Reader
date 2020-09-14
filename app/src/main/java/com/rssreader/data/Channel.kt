package com.rssreader.data

import androidx.room.Entity
import androidx.room.PrimaryKey

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
)