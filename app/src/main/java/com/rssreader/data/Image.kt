package com.rssreader.data

import com.google.gson.Gson

data class Image(val url: String?, val title: String?, val link: String?) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}