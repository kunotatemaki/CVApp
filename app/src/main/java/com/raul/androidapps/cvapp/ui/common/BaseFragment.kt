package com.raul.androidapps.cvapp.ui.common

import android.os.Bundle
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.raul.androidapps.cvapp.databinding.CVAppBindingComponent
import com.raul.androidapps.cvapp.network.Resource
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: CVAppViewModelFactory

    @Inject
    protected lateinit var cvAppBindingComponent: CVAppBindingComponent

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getSwipeToRefresh()?.setOnRefreshListener {
            getViewModel().let { vm ->
                vm.fetchCVFromServer(vm.getGistId())
            }
        }

        getViewModel().getLoadingState().observe({ lifecycle }) {
            it?.let { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> hideLoading()
                    Resource.Status.ERROR -> {
                        hideLoading()
                        showError(resource.message)
                    }
                    Resource.Status.LOADING -> showLoading()
                }
            }
        }
    }

    abstract fun getSwipeToRefresh(): SwipeRefreshLayout?

    abstract fun getViewModel(): BaseViewModel

    private fun showLoading() {
        getSwipeToRefresh()?.isRefreshing = true
    }

    private fun hideLoading() {
        getSwipeToRefresh()?.isRefreshing = false
    }

    private fun showError(message: String?) {
        message?.let {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}
