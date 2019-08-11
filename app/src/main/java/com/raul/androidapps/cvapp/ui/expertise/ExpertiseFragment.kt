package com.raul.androidapps.cvapp.ui.expertise

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.raul.androidapps.cvapp.R

class ExpertiseFragment : Fragment() {

    companion object {
        fun newInstance() = ExpertiseFragment()
    }

    private lateinit var viewModel: ExpertiseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.expertise_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExpertiseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
