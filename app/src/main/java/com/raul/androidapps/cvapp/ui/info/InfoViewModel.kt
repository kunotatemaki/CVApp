package com.raul.androidapps.cvapp.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raul.androidapps.cvapp.repository.Repository
import com.raul.androidapps.cvapp.ui.common.BaseViewModel
import javax.inject.Inject

class InfoViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel(repository) {

    private val description: MutableLiveData<String> = MutableLiveData()

    fun getDescription(): LiveData<String> {
        repository.geUserInfo(getGistId()).observeForever {
            it?.let {
                description.postValue(it.description)
            }
        }
        return description
    }

}

