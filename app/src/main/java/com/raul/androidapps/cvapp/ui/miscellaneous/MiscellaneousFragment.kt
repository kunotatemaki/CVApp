package com.raul.androidapps.cvapp.ui.miscellaneous

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.MiscellaneousFragmentBinding
import com.raul.androidapps.cvapp.ui.common.BaseFragment
import com.raul.androidapps.cvapp.ui.common.BaseViewModel

class MiscellaneousFragment : BaseFragment() {

    private lateinit var binding: MiscellaneousFragmentBinding
    private lateinit var viewModel: MiscellaneousViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.miscellaneous_fragment,
            container,
            false,
            cvAppBindingComponent
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MiscellaneousViewModel::class.java)
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun getSwipeToRefresh(): SwipeRefreshLayout? =
        null

    override fun getViewModel(): BaseViewModel = viewModel

}
