package com.raul.androidapps.cvapp.ui.expertise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.ExpertiseFragmentBinding
import com.raul.androidapps.cvapp.ui.common.BaseFragment
import com.raul.androidapps.cvapp.ui.common.BaseViewModel

class ExpertiseFragment : BaseFragment() {


    private lateinit var binding: ExpertiseFragmentBinding
    private lateinit var viewModel: ExpertiseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.expertise_fragment,
            container,
            false,
            cvAppBindingComponent
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExpertiseViewModel::class.java)
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun getSwipeToRefresh(): SwipeRefreshLayout? =
        null

    override fun getViewModel(): BaseViewModel = viewModel

}
