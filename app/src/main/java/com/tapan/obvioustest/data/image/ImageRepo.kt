package com.tapan.obvioustest.data.image

import com.tapan.obvioustest.data.core.Resource
import com.tapan.obvioustest.data.image.model.ImageNetworkModel
import com.tapan.obvioustest.data.image.source.ImageDataSource
import kotlinx.coroutines.flow.Flow

interface ImageRepo {
    fun getImages(): Flow<Resource<List<ImageNetworkModel>>>
}

class ImageRepoImpl(private val imageDataSource: ImageDataSource) : ImageRepo {
    override fun getImages(): Flow<Resource<List<ImageNetworkModel>>> {
        return imageDataSource.getImages()
    }


}