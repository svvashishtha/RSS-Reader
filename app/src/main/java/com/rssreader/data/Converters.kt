package com.rssreader.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun imageToString(image: Image?): String? {
            return image?.toString()
        }

        @TypeConverter
        @JvmStatic
        fun stringToImage( jsonString: String?): Image?{
            return if (jsonString !=null) Gson().fromJson(jsonString, Image::class.java) else null
        }
    }
}