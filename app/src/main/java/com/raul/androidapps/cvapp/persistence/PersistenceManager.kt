package com.raul.androidapps.cvapp.persistence

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.model.Profile

interface PersistenceManager {

    suspend fun getUserInfo(gistId: String): LiveData<Profile>

    suspend fun insertUserInfo(profile: Profile, gistId: String)

    suspend fun getListOfTasks(gistId: String): LiveData<List<String>>

    suspend fun removeListOfTasks(gistId: String, lastPosition: Int)

}