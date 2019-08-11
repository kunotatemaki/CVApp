package com.raul.androidapps.cvapp.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.raul.androidapps.cvapp.BuildConfig
import com.raul.androidapps.cvapp.network.NetworkServiceFactory
import com.raul.androidapps.cvapp.network.Resource
import com.raul.androidapps.cvapp.preferences.PreferencesManager
import com.raul.androidapps.cvapp.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


abstract class BaseViewModel constructor(
    private val repository: Repository
): ViewModel() {

    fun fetchCVFromServer(gistId: String){
        repository.fetchFromNetwork(gistId = gistId, forceFetch = true)
    }

    fun getLoadingState(): LiveData<Resource<Void>> = repository.getLoadingState()

    fun getGistId(): String = BuildConfig.CV_GIST_ID


}