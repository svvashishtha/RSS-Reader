package com.rssreader.data

import com.google.gson.annotations.SerializedName

data class AddChannelRequestBody(@SerializedName("url") val url: String)