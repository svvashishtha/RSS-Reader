package com.rssreader.ui.channels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rssreader.data.Channel
import com.rssreader.data.ChannelDao
import com.rssreader.network.ApiResponse
import com.rssreader.network.RSSService
import com.rssreader.util.SuggestedChannelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
    private val channelDao: ChannelDao,
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