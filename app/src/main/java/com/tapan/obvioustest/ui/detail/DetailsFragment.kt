package com.tapan.obvioustest.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.tapan.obvioustest.R
import com.tapan.obvioustest.ui.MainViewModel
import com.tapan.obvioustest.ui.detail.adapter.DetailAdapter
import com.tapan.obvioustest.ui.grid.model.ImageModel
import com.tapan.obvioustest.util.handleResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.details_fragment.*

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.details_fragment) {


    private val viewModel: DetailsViewModel by viewModels<DetailsViewModel>()
    private val mainViewModel: MainViewModel by activityViewModels<MainViewModel>()

    private val detailAdapter = DetailAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        pagger2.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = detailAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mainViewModel.currentPosition = position
                }
            })
        }

        attachLiveData()
    }

    private fun attachLiveData() {
        handleResponse(
            viewModel.imageModelResponse,
            progressView = pbLoading
        ) {
            it.map { networkModel ->
                with(networkModel) {
                    ImageModel(title, explanation, date, url, hdurl)
                }
            }.apply {
                detailAdapter.updateDataSet(this)
                goToSelectedImage()
            }
        }


    }

    private fun goToSelectedImage() {
        arguments?.apply {
            DetailsFragmentArgs.fromBundle(this).position.let {
                pagger2.setCurrentItem(it, false)
            }
        }
    }

}