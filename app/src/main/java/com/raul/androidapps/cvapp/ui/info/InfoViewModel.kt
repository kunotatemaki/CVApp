package com.raul.androidapps.cvapp.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raul.androidapps.cvapp.repository.Repository
import javax.inject.Inject

class InfoViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val description: MutableLiveData<String> = MutableLiveData()


    fun getDescription(gist: String): LiveData<String> {
        repository.geUserInfo(gist).observeForever {
            it?.let {
                description.postValue(it.description)
            }
        }
        return description
    }

}

