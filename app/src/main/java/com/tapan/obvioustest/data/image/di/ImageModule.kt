package com.tapan.obvioustest.data.image.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.tapan.obvioustest.data.image.ImageRepo
import com.tapan.obvioustest.data.image.ImageRepoImpl
import com.tapan.obvioustest.data.image.source.ImageDataSource
import com.tapan.obvioustest.data.image.source.ImageDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import kotlin.contracts.ExperimentalContracts

@Module
@InstallIn(ApplicationComponent::class)
abstract class ImageModule {

    @ExperimentalContracts
    @Binds
    @Singleton
    abstract fun getImageDataSource(imageDataSourceImpl: ImageDataSourceImpl): ImageDataSource

    @Binds
    @ExperimentalContracts
    @Singleton
    abstract fun getImageRepo(repoImpl: ImageRepoImpl): ImageRepo
}