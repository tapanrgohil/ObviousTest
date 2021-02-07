package com.tapan.obvioustest.data.image.source

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tapan.obvioustest.R
import com.tapan.obvioustest.data.core.Resource
import com.tapan.obvioustest.data.core.getFlow
import com.tapan.obvioustest.data.image.model.ImageNetworkModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.contracts.ExperimentalContracts

@ExperimentalContracts
@Singleton
class ImageDataSourceImpl @Inject constructor(
    private val gson: Gson,
    private val context: Application
) : ImageDataSource {
    override fun getImages(): Flow<Resource<List<ImageNetworkModel>>> {
        return getFlow(
            remote = {
                delay(1500) // to emulate network delay
                val dataJson = context.resources.openRawResource(R.raw.data)
                    .bufferedReader()
                    .use {
                        it.readText()
                    }
                val dataType = object : TypeToken<List<ImageNetworkModel>>() {}.type
                Response.success(gson.fromJson<List<ImageNetworkModel>>(dataJson, dataType))
            }, mapper = {
                it
            }
        )
    }
}
