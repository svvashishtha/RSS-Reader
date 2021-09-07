package com.rssreader.layman.ui.feed

import com.rssreader.layman.data.Channel
import com.rssreader.layman.data.FeedWrapper
import com.rssreader.layman.network.ApiResponse
import com.rssreader.layman.network.RSSService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(val rssService: RSSService) : FeedRepository {
    override suspend fun fetchRssFeedForChannel(channel: Channel): ApiResponse<FeedWrapper> {
        return withContext(Dispatchers.IO){
            try{
                val response = rssService.getRssService().getFeedAsync(channel.id).execute()
                val data = response.body()
                if (response.isSuccessful && data!=null)
                {
                    ApiResponse.success(data)
                }else{
                    ApiResponse.error(null, response.code().toString())
                }
            }catch (e:Exception)
            {
                ApiResponse.error(null,e.message?:"Something went wrong")
            }
        }
    }
}