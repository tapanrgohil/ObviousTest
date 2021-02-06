package com.tapan.obvioustest.data.image.source

import com.tapan.obvioustest.data.core.Resource
import com.tapan.obvioustest.data.image.model.ImageNetworkModel
import kotlinx.coroutines.flow.Flow

//This can be local and remote sources
interface ImageDataSource {

    fun getImages(): Flow<Resource<List<ImageNetworkModel>>>
}