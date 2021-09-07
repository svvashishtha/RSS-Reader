package com.rssreader.layman.di

import com.rssreader.ui.FakeChannelRepository
import com.rssreader.ui.FakeFeedRepository
import com.rssreader.layman.ui.channels.MyChannelRepository
import com.rssreader.layman.ui.feed.FeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
//Repositories will live same as the activity that requires them
@InstallIn(SingletonComponent::class)
abstract class FakeRepositoryModule {

    @Binds
    abstract fun providesChannelRepository(impl: FakeChannelRepository): MyChannelRepository
    @Binds
    abstract fun providesRssFeedRepository(impl: FakeFeedRepository): FeedRepository
}