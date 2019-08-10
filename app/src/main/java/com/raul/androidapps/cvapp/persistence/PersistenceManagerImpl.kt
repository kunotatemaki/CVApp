package com.raul.androidapps.cvapp.persistence

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.databases.CVAppDatabase
import com.raul.androidapps.cvapp.persistence.entities.UserInfoEntity
import javax.inject.Inject

class PersistenceManagerImpl @Inject constructor(
    private val db: CVAppDatabase
) : PersistenceManager {

    override suspend fun getUserInfo(gistId: String): LiveData<Profile> =
        db.userInfoDao().getUserInfo(gistId)

    override suspend fun insertUserInfo(profile: Profile, gistId: String) {
        db.userInfoDao().insert(UserInfoEntity.fromProfile(profile, gistId))
    }

    override suspend fun getListOfTasks(gistId: String): LiveData<List<String>> =
        db.taskDao().getListOfTasks(gistId)

    override suspend fun removeListOfTasks(gistId: String, lastPosition: Int) {
        db.taskDao().removeListOfTasks(gistId, lastPosition)
    }
}

