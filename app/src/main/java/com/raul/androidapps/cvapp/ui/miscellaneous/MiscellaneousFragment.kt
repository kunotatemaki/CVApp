package com.raul.androidapps.cvapp.ui.miscellaneous

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.MiscellaneousFragmentBinding
import com.raul.androidapps.cvapp.ui.common.BaseFragment

class MiscellaneousFragment : BaseFragment() {

    private lateinit var binding: MiscellaneousFragmentBinding
    private lateinit var viewModel: MiscellaneousViewModel
    private lateinit var adapter: MiscellaneousAdapter

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
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MiscellaneousViewModel::class.java)
        adapter = MiscellaneousAdapter(cvAppBindingComponent)
        binding.miscellaneousList.adapter = adapter
        viewModel.getMiscellaneous().observe({ lifecycle }) {
            it?.let {
                adapter.updateItems(it)
            }
        }
    }

}
