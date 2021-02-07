package com.tapan.obvioustest.data.image

import com.tapan.obvioustest.data.core.Resource
import com.tapan.obvioustest.data.image.model.ImageNetworkModel
import com.tapan.obvioustest.data.image.source.ImageDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.contracts.ExperimentalContracts

interface ImageRepo {
    fun getImages(): Flow<Resource<List<ImageNetworkModel>>>
}

@ExperimentalContracts
@Singleton
class ImageRepoImpl @Inject
constructor(private val imageDataSource: ImageDataSource) : ImageRepo {
    override fun getImages(): Flow<Resource<List<ImageNetworkModel>>> {
        return imageDataSource.getImages()
    }
}