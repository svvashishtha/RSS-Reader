package com.rssreader.di

import com.rssreader.ui.channels.ChannelRepositoryImpl
import com.rssreader.ui.channels.MyChannelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
//Repositories will live same as the activity that requires them
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesChannelRepository(impl: ChannelRepositoryImpl): MyChannelRepository

}