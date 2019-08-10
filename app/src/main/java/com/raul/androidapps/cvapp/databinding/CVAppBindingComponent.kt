package com.raul.androidapps.cvapp.databinding

import androidx.databinding.DataBindingComponent
import javax.inject.Inject

class CVAppBindingComponent @Inject constructor(private val cvAppBindingAdapters: CVAppBindingAdapters) : DataBindingComponent {
    override fun getCVAppBindingAdapters(): CVAppBindingAdapters {
        return cvAppBindingAdapters
    }
}