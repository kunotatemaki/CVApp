package com.raul.androidapps.cvapp.ui.skill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.SkillFragmentBinding
import com.raul.androidapps.cvapp.ui.common.BaseFragment
import com.raul.androidapps.cvapp.ui.common.BaseViewModel

class SkillFragment : BaseFragment() {


    private lateinit var binding: SkillFragmentBinding
    private lateinit var viewModel: SkillViewModel
    private lateinit var adapter: SkillsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.skill_fragment,
            container,
            false,
            cvAppBindingComponent
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SkillViewModel::class.java)
        super.onActivityCreated(savedInstanceState)
        adapter = SkillsAdapter(bindingComponent = cvAppBindingComponent)
        binding.skillList.adapter = adapter
        viewModel.getSkills().observe({ lifecycle }) {
            it?.let {
                adapter.updateItems(it)
            }
        }
    }

    override fun getSwipeToRefresh(): SwipeRefreshLayout? =
        binding.swipeRefresh

    override fun getViewModel(): BaseViewModel = viewModel

}
