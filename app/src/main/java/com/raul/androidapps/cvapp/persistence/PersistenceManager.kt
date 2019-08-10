package com.raul.androidapps.cvapp.persistence

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.model.Profile

interface PersistenceManager {

    suspend fun getUserInfo(gistId: String): LiveData<Profile>

}