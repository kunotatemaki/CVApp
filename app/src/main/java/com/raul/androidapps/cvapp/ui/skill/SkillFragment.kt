package com.raul.androidapps.cvapp.ui.skill

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.SkillFragmentBinding
import com.raul.androidapps.cvapp.databinding.SkillFragmentBindingImpl
import com.raul.androidapps.cvapp.ui.common.BaseFragment
import com.raul.androidapps.cvapp.ui.common.BaseViewModel

class SkillFragment : BaseFragment() {


    private lateinit var binding: SkillFragmentBinding
    private lateinit var viewModel: SkillViewModel

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
        // TODO: Use the ViewModel
    }

    override fun getSwipeToRefresh(): SwipeRefreshLayout? =
        null

    override fun getViewModel(): BaseViewModel = viewModel

}
