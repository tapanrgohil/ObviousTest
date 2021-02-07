package com.tapan.obvioustest.ui.grid

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tapan.obvioustest.data.image.ImageRepo
import javax.inject.Inject

class GridViewModel @ViewModelInject
constructor(val imageRepo: ImageRepo) : ViewModel() {


    val imageModelResponse =
        imageRepo.getImages()
            .asLiveData(viewModelScope.coroutineContext)


}