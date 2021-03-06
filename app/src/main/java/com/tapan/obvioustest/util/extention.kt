package com.tapan.obvioustest.util

import android.view.View
import android.widget.ImageView
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.tapan.obvioustest.R
import com.tapan.obvioustest.data.core.Resource
import com.tapan.obvioustest.data.core.Status
import com.tapan.obvioustest.exception.SnackbarManager.handleErrorResponse


fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.visible(boolean: Boolean) {
    if (boolean) {
        visible()
    } else {
        gone()
    }
}

fun View.visibleInVisible(boolean: Boolean) {
    if (boolean) {
        visible()
    } else {
        invisible()
    }
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, androidx.lifecycle.Observer(body))


fun <R> Fragment.handleResponse(
    liveData: LiveData<Resource<R>>,
    progressView: View? = null,
    @UiThread
    swipeRefreshLayout: SwipeRefreshLayout? = null,
    process: (R) -> Unit
) {
    observe(liveData) {
        it?.apply {
            when (this.status) {
                Status.SUCCESS -> {
                    data?.let { it1 -> process.invoke(it1) }
                    swipeRefreshLayout?.isRefreshing = false
                    progressView?.gone()
                }
                Status.ERROR -> {
                    swipeRefreshLayout?.isRefreshing = false
                    throwable?.let { exception ->
                        handleErrorResponse(it.retrofitResponse, it.throwable)
                    }
                    progressView?.gone()
                }
                Status.LOADING -> {
                    swipeRefreshLayout?.isRefreshing = true
                    progressView?.visible()
                }
            }
        }
    }
}

fun ImageView.load(url: String, thumb: String) {
    val requestOption = RequestOptions()
        .error(android.R.drawable.progress_horizontal).centerCrop()
    Glide.with(this)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .thumbnail(
            Glide.with(this)
                .load(thumb)
                .apply(requestOption)
        )
        .apply(requestOption)
        .into(this)
}

fun ImageView.load(url: String) {
    val requestOption = RequestOptions()
        .placeholder(android.R.drawable.ic_notification_clear_all).centerCrop()
    Glide.with(this)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(requestOption)
        .into(this)
}