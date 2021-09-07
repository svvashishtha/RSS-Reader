package com.rssreader.layman.ui.addChannel

import com.rssreader.layman.data.AddChannelRequestBody
import com.rssreader.layman.data.Status
import com.rssreader.layman.network.ApiResponse

interface AddChannelRepository {
    suspend fun addNewChannel(addChannelRequestBody: AddChannelRequestBody): ApiResponse<Status>
}