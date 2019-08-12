package com.raul.androidapps.cvapp.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.InfoFragmentBinding
import com.raul.androidapps.cvapp.ui.common.BaseFragment

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
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(InfoViewModel::class.java)

        viewModel.getDescription().observe({ this.lifecycle }) {
            it?.let { description ->
                binding.description = description
            }
        }
    }

}
