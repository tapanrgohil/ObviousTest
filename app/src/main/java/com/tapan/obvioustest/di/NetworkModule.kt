package com.tapan.obvioustest.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideGson(): Gson {
        //custom type adapter shall be added if required
        return Gson()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactor(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }


    //Network related dependencies will be setup here like retrofit and all


}