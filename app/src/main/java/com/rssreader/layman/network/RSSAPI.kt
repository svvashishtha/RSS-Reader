package com.rssreader.layman.network

import com.rssreader.layman.data.AddChannelRequestBody
import com.rssreader.layman.data.Channel
import com.rssreader.layman.data.FeedWrapper
import com.rssreader.layman.data.Status
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

public interface RSSAPI {

    @GET("/feed/{id}")
    fun getFeedAsync(@Path("id") id: String ): Call<FeedWrapper>

    @GET("/channel/get")
    fun  getChannels(): Call<List<Channel>>

    @POST("/channel/add")
    fun addChannel(@Body addChannelRequestBody: AddChannelRequestBody): Call<Status>
}