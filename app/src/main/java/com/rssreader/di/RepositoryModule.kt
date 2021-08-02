package com.rssreader.di

import com.rssreader.ui.channels.ChannelRepositoryImpl
import com.rssreader.ui.channels.MyChannelRepository
import com.rssreader.ui.feed.FeedRepository
import com.rssreader.ui.feed.FeedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
//Repositories will live same as the activity that requires them
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesChannelRepository(impl: ChannelRepositoryImpl): MyChannelRepository

    @Binds
    abstract fun providesRssFeedRepository(impl: FeedRepositoryImpl): FeedRepository
}