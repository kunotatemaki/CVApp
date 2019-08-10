package com.raul.androidapps.cvapp.persistence

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.databases.CVAppDatabase
import javax.inject.Inject

class PersistenceManagerImpl @Inject constructor(
    private val db: CVAppDatabase
) : PersistenceManager {

    override suspend fun getUserInfo(gistId: String): LiveData<Profile> =
        db.userInfoDao().getUserInfo(gistId)

}

