package com.raul.androidapps.cvapp.ui.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.EducationFragmentBinding
import com.raul.androidapps.cvapp.ui.common.BaseFragment
import com.raul.androidapps.cvapp.ui.common.BaseViewModel

class EducationFragment : BaseFragment() {

    private lateinit var binding: EducationFragmentBinding
    private lateinit var viewModel: EducationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.education_fragment,
            container,
            false,
            cvAppBindingComponent
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EducationViewModel::class.java)
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun getSwipeToRefresh(): SwipeRefreshLayout? =
        null

    override fun getViewModel(): BaseViewModel = viewModel

}
