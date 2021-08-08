package com.rssreader.network

import com.rssreader.data.Channel
import com.rssreader.data.FeedWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

public interface RSSAPI {

    @GET("/feed/{id}")
    fun getFeedAsync(@Path("id") id: String ): Call<FeedWrapper>

    @GET("/channel/get")
    fun  getChannels(): Call<List<Channel>>
}