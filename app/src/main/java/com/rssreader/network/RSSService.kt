package com.rssreader.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Inject


class RSSService @Inject constructor(){

    private fun getRetroFit(baseURL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(buildHttpClient())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
    }

    fun getRssService(baseURL: String): RSSAPI {
        return getRetroFit(baseURL).create(RSSAPI::class.java)
    }
    private fun buildHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}