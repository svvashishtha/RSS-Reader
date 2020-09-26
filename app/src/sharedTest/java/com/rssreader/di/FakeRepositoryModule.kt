package com.rssreader.di

import com.rssreader.ui.FakeChannelRepository
import com.rssreader.ui.channels.MyChannelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent

@Module
//Repositories will live same as the activity that requires them
@InstallIn(ApplicationComponent::class)
abstract class FakeRepositoryModule {

    @Binds
    abstract fun providesChannelRepository(impl: FakeChannelRepository): MyChannelRepository
}