package com.raul.androidapps.cvapp.repository

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.network.NetworkServiceFactory
import com.raul.androidapps.cvapp.network.Resource
import com.raul.androidapps.cvapp.persistence.PersistenceManager
import com.raul.androidapps.cvapp.persistence.relations.MiscellaneousWithAllInfo
import com.raul.androidapps.cvapp.preferences.PreferencesConstants
import com.raul.androidapps.cvapp.preferences.PreferencesManager
import com.raul.androidapps.cvapp.utils.RateLimiter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(
    private val networkServiceFactory: NetworkServiceFactory,
    private val preferencesManager: PreferencesManager,
    private val persistenceManager: PersistenceManager
) {

    private val loadingState: MutableLiveData<Resource<Void>> = MutableLiveData()

    init {
        loadingState.value = Resource.success(null)
    }

    fun getLoadingState(): LiveData<Resource<Void>> = loadingState

    fun getUserInfo(gistId: String): LiveData<Profile> {
        fetchFromNetwork(gistId, forceFetch = false)
        return persistenceManager.getUserInfo(gistId)
    }

    fun getSkills(gistId: String): LiveData<List<String>> {
        fetchFromNetwork(gistId, forceFetch = false)
        return persistenceManager.getListOfSkills(gistId)
    }

    fun getEducation(gistId: String): LiveData<List<String>> {
        fetchFromNetwork(gistId, forceFetch = false)
        return persistenceManager.getEducation(gistId)
    }

    fun getMiscellaneous(gistId: String): LiveData<List<MiscellaneousWithAllInfo>> {
        fetchFromNetwork(gistId, forceFetch = false)
        return persistenceManager.getListOfMiscellaneous(gistId)
    }

    fun fetchFromNetwork(gistId: String, forceFetch: Boolean) {
        if (forceFetch || shouldFetch()) {
            GlobalScope.launch {
                fetchFromNetworkAsync(gistId)
            }
        }
    }

    @Suppress("UselessCallOnCollection")
    @VisibleForTesting
    suspend fun fetchFromNetworkAsync(gistId: String) =
        withContext(Dispatchers.IO) {
            loadingState.postValue(Resource.loading(null))
            val response = networkServiceFactory.getServiceInstance().getCVInfo(gistId)
            if (response.isSuccessful) {
                loadingState.postValue(Resource.success(null))
                preferencesManager.setLongIntoPreferences(
                    PreferencesConstants.PROPERTY_FETCHED_TIMESTAMP,
                    System.currentTimeMillis()
                )
                response.body()?.files?.profileContent?.toProfile()?.let {
                    persistenceManager.insertUserInfo(it, gistId)
                }

                response.body()?.files?.educationContent?.toListOfEducationItems()?.let {
                    persistenceManager.insertEducation(it.filterNotNull(), gistId)
                }
                response.body()?.files?.expertiseContent?.toListOfExpertiseItems()?.let {
                    persistenceManager.insertListOfCompanies(it.filterNotNull(), gistId)
                }
                response.body()?.files?.skillContent?.toListOfSkillItems()?.let {
                    persistenceManager.insertListOfSkills(it.filterNotNull(), gistId)
                }
                response.body()?.files?.miscellaneousContent?.toListOfMiscellaneousItems()
                    ?.let {
                        persistenceManager.insertListOfMiscellaneous(it.filterNotNull(), gistId)
                    }
            } else {
                loadingState.postValue(Resource.error("Error Downloading info", null))
            }
        }

    @VisibleForTesting
    fun shouldFetch(): Boolean {
        val lastFetched =
            preferencesManager.getLongFromPreferences(PreferencesConstants.PROPERTY_FETCHED_TIMESTAMP)
        return RateLimiter(5, TimeUnit.MINUTES).shouldFetch(lastFetched)
    }
}