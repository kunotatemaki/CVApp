package com.raul.androidapps.cvapp.ui.common

import androidx.lifecycle.ViewModel
import com.raul.androidapps.cvapp.BuildConfig
import com.raul.androidapps.cvapp.repository.Repository


abstract class BaseViewModel constructor(
    private val repository: Repository
) : ViewModel() {

    fun fetchCVFromServer(gistId: String) {
        repository.fetchFromNetwork(gistId = gistId, forceFetch = true)
    }

    fun getGistId(): String = BuildConfig.CV_GIST_ID


}