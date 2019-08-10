package com.raul.androidapps.cvapp.ui.common

import com.raul.androidapps.cvapp.databinding.CVAppBindingComponent
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: CVAppViewModelFactory

    @Inject
    protected lateinit var cvAppBindingComponent: CVAppBindingComponent

}