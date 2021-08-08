package com.rssreader.ui.feed

import com.google.gson.Gson
import com.rssreader.data.Channel
import com.rssreader.data.FeedWrapper
import com.rssreader.network.ApiResponse
import com.rssreader.network.RSSService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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