package com.rssreader.data

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Media(@SerializedName("href") @Expose val href: String?,
                 @SerializedName("type") @Expose val type: String?
):Serializable{
    override fun toString(): String {
        return Gson().toJson(this)
    }
}