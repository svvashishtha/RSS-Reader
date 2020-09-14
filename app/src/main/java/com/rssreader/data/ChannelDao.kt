package com.rssreader.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChannelDao {

    @Query("SELECT * FROM channel")
    fun getAllChannels(): List<Channel>

    @Insert
    fun insertChannel(channel: Channel)
}