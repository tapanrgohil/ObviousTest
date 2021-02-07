package com.tapan.obvioustest.ui.grid

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.UiThread
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.jakewharton.threetenabp.AndroidThreeTen.init
import com.tapan.obvioustest.R
import com.tapan.obvioustest.data.core.Resource
import com.tapan.obvioustest.ui.grid.adapter.GridAdapter
import com.tapan.obvioustest.ui.grid.model.ImageModel
import com.tapan.obvioustest.util.handleResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.grid_fragment.*

@AndroidEntryPoint
class GridFragment : Fragment(R.layout.grid_fragment) {

    private val viewModel by viewModels<GridViewModel>()

    private val gridAdapter = GridAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dispatcher = requireActivity().onBackPressedDispatcher
        dispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val dialog = MaterialDialog(requireContext(), BottomSheet(LayoutMode.WRAP_CONTENT))
                dialog.show {
                    title(text = "Are you sure?")
                    message(text = "Are you sure you want to exit?")
                    positiveButton(text = "Yes") { dialog ->
                        isEnabled = false
                        dialog.dismiss()
                        requireActivity().onBackPressed()
                    }
                    negativeButton(text = "No") { dialog ->
                        dialog.dismiss()
                    }
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        rvImages.apply {
            layoutManager = GridLayoutManager(context, 3)
            rvImages.adapter = gridAdapter

        }
        srImageList.setOnRefreshListener {
            srImageList.isRefreshing = false
        }
        attachLiveData()
    }

    private fun attachLiveData() {
        handleResponse(
            viewModel.imageModelResponse,
            swipeRefreshLayout = srImageList,
            progressView = pbLoading
        ) {
            it.map { networkModel ->
                with(networkModel) {
                    ImageModel(title, explanation, date, url, hdurl)
                }
            }.apply {
                gridAdapter.updateDataSet(this)
            }
        }
    }


}