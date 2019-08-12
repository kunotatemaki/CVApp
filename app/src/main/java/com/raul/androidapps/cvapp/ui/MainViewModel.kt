package com.raul.androidapps.cvapp.ui

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.network.Resource
import com.raul.androidapps.cvapp.repository.Repository
import com.raul.androidapps.cvapp.ui.common.BaseViewModel
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel(repository) {

    fun getProfile(): LiveData<Profile> = repository.geUserInfo(getGistId())

    fun getLoadingState(): LiveData<Resource<Void>> = repository.getLoadingState()

}

//todo splash icon
//todo test in repo