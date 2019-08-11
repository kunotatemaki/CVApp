package com.raul.androidapps.cvapp.ui

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.repository.Repository
import com.raul.androidapps.cvapp.ui.common.BaseViewModel
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel(repository) {

    fun getProfile(): LiveData<Profile> = repository.geUserInfo(getGistId())

//    fun fetchCVInfoAsync(gistId: String){
//        GlobalScope.launch {
//
//            val response = networkServiceFactory.getServiceInstance().getCVInfo(gistId)
//            val profile = response.body()?.files?.profileContent?.toProfile()
//            val listOfEducationItems = response.body()?.files?.educationContent?.toListOfEducationItems()
//            val listOfExpertiseItems = response.body()?.files?.expertiseContent?.toListOfExpertiseItems()
//            val skills = response.body()?.files?.skillContent?.toListOfSkillItems()
//            val miscellaneous = response.body()?.files?.miscellaneousContent?.toListOfMiscellaneousItems()
//            Timber.d("")
//        }
//    }
}

//todo fix pull to refresh when expanding
//todo splash icon
//todo test in repo
//header