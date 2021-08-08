package com.rssreader.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mediaToString(media: Media?): String? {
            return media?.toString()
        }

        @TypeConverter
        @JvmStatic
        fun stringToMedia( jsonString: String?): Media?{
            return if (jsonString !=null) Gson().fromJson(jsonString, Media::class.java) else null
        }
    }
}