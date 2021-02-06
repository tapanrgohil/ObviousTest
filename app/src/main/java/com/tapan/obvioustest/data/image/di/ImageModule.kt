package com.tapan.obvioustest.data.image.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.tapan.obvioustest.data.image.ImageRepo
import com.tapan.obvioustest.data.image.ImageRepoImpl
import com.tapan.obvioustest.data.image.source.ImageDataSource
import com.tapan.obvioustest.data.image.source.ImageDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ImageModule {

    @Provides
    fun getImageDataSource(context: Application, gson: Gson): ImageDataSource {
        return ImageDataSourceImpl(gson, context)
    }

    @Provides
    fun getImageRepo(imageDataSource: ImageDataSource): ImageRepo {
        return ImageRepoImpl(imageDataSource)
    }
}