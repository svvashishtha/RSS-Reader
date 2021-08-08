package com.rssreader.network

import com.rssreader.data.AddChannelRequestBody
import com.rssreader.data.Channel
import com.rssreader.data.FeedWrapper
import com.rssreader.data.Status
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