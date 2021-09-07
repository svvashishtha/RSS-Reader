package com.rssreader.layman.ui.channels

import com.rssreader.layman.data.Channel
import com.rssreader.layman.network.ApiResponse
import com.rssreader.layman.network.RSSService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
    private val rssService: RSSService
) :
    MyChannelRepository {
    override suspend fun getSuggestedChannels(): ApiResponse<List<Channel>> {


        return withContext(Dispatchers.IO) {
            try {
                val response = rssService.getRssService().getChannels().execute()
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