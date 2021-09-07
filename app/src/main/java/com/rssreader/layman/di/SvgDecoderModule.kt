package com.rssreader.layman.di

import android.content.Context
import coil.decode.SvgDecoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@InstallIn(ActivityComponent::class)
@Module
class SvgDecoderModule {

    @Provides
    fun provideSvgDecoder(@ActivityContext context: Context): SvgDecoder {
        return SvgDecoder(context)
    }
}