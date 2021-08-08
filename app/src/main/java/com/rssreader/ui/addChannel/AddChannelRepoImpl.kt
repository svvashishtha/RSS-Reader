package com.rssreader.ui.addChannel

import com.rssreader.data.AddChannelRequestBody
import com.rssreader.data.Status
import com.rssreader.network.ApiResponse
import com.rssreader.network.RSSService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class AddChannelRepoImpl @Inject constructor(val rssService: RSSService) : AddChannelRepository {
    override suspend fun addNewChannel(addChannelRequestBody: AddChannelRequestBody): ApiResponse<Status> {
        return withContext(Dispatchers.IO) {
            try {
                val response = rssService.getRssService().addChannel(addChannelRequestBody).execute()
                val data = response.body()
                if (response.isSuccessful && data != null) {
                    ApiResponse.success(data)
                } else {
                    ApiResponse.error(null, response.code().toString())
                }
            } catch (e: Exception) {
                ApiResponse.error(null, e.message ?: "Something went wrong")
            }
        }
    }

}