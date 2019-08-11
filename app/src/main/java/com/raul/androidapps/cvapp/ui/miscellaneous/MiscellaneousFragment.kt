package com.raul.androidapps.cvapp.ui.miscellaneous

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.raul.androidapps.cvapp.R

class MiscellaneousFragment : Fragment() {

    companion object {
        fun newInstance() = MiscellaneousFragment()
    }

    private lateinit var viewModel: MiscellaneousViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.miscellaneous_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MiscellaneousViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
