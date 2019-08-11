package com.raul.androidapps.cvapp.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.raul.androidapps.cvapp.BuildConfig
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.InfoFragmentBinding
import com.raul.androidapps.cvapp.ui.MainViewModel
import com.raul.androidapps.cvapp.ui.common.BaseFragment
import com.raul.androidapps.cvapp.ui.common.BaseViewModel

class InfoFragment : BaseFragment() {

    private lateinit var binding: InfoFragmentBinding
    private lateinit var viewModel: InfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.info_fragment,
            container,
            false,
            cvAppBindingComponent
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(InfoViewModel::class.java)
        super.onActivityCreated(savedInstanceState)

        viewModel.getDescription().observe({ this.lifecycle }) {
            it?.let { description ->
                binding.description.text = description
            }
        }
    }

    override fun getSwipeToRefresh(): SwipeRefreshLayout =
        binding.swipeRefresh

    override fun getViewModel(): BaseViewModel = viewModel

}
