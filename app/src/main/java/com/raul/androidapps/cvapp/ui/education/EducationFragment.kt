package com.raul.androidapps.cvapp.ui.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.EducationFragmentBinding
import com.raul.androidapps.cvapp.ui.common.BaseFragment

class EducationFragment : BaseFragment() {

    private lateinit var binding: EducationFragmentBinding
    private lateinit var viewModel: EducationViewModel
    private lateinit var adapter: EducationAdapter

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
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(EducationViewModel::class.java)
        adapter = EducationAdapter(bindingComponent = cvAppBindingComponent)
        binding.educationList.adapter = adapter
        viewModel.getEducation().observe({ lifecycle }) {
            it?.let {
                adapter.updateItems(it)
            }
        }
    }

}
