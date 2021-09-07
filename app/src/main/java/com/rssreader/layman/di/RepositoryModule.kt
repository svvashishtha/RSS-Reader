package com.rssreader.layman.di

import com.rssreader.layman.ui.addChannel.AddChannelRepoImpl
import com.rssreader.layman.ui.addChannel.AddChannelRepository
import com.rssreader.layman.ui.channels.ChannelRepositoryImpl
import com.rssreader.layman.ui.channels.MyChannelRepository
import com.rssreader.layman.ui.feed.FeedRepository
import com.rssreader.layman.ui.feed.FeedRepositoryImpl
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

    @Binds
    abstract fun providesAddChannelRepository(impl: AddChannelRepoImpl): AddChannelRepository
}