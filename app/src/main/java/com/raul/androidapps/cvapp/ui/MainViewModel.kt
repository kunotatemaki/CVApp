package com.raul.androidapps.cvapp.ui

import androidx.lifecycle.ViewModel
import com.raul.androidapps.cvapp.BuildConfig
import com.raul.androidapps.cvapp.network.NetworkServiceFactory
import com.raul.androidapps.cvapp.preferences.PreferencesManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val networkServiceFactory: NetworkServiceFactory
): ViewModel() {

    init {
        //todo remove this
//        fetchCVInfoAsync(BuildConfig.CV_GIST_ID)
    }

//    fun fetchCVInfoAsync(gistId: String){
//        GlobalScope.launch {
//            //todo create viemodel scope
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


//todo splash icon
//todo test in repo
//header
//remove this class