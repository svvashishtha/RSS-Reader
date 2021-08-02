package com.rssreader.network

import com.rssreader.data.FeedWrapper
import retrofit2.http.GET

public interface RSSAPI {

    @GET(".")
    suspend fun getFeedAsync(): FeedWrapper
}