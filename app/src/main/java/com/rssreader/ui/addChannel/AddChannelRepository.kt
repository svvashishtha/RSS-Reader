package com.rssreader.ui.addChannel

import androidx.lifecycle.LiveData
import com.rssreader.data.AddChannelRequestBody
import com.rssreader.data.Channel
import com.rssreader.data.Status
import com.rssreader.network.ApiResponse

interface AddChannelRepository {
    suspend fun addNewChannel(addChannelRequestBody: AddChannelRequestBody): ApiResponse<Status>
}