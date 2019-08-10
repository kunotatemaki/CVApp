package com.raul.androidapps.cvapp.persistence

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.model.Expertise
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.entities.CompanyEntity
import com.raul.androidapps.cvapp.persistence.relations.CompanyWithAllInfo

interface PersistenceManager {

    suspend fun getUserInfo(gistId: String): LiveData<Profile>

    suspend fun insertUserInfo(profile: Profile, gistId: String)

    suspend fun getListOfTasks(gistId: String, companyId: Int): LiveData<List<String>>

    suspend fun insertListOfTasks(
        tasks: List<String>,
        gistId: String,
        companyId: Int
    )

    suspend fun removeListOfTasks(gistId: String, companyId: Int, lastPosition: Int)

    suspend fun getListOfAchievements(gistId: String, companyId: Int): LiveData<List<String>>

    suspend fun insertListOfAchievements(
        achievements: List<String>,
        gistId: String,
        companyId: Int
    )

    suspend fun removeListOfAchievements(gistId: String, companyId: Int, lastPosition: Int)

    suspend fun getListOfCompanies(gistId: String): LiveData<List<CompanyWithAllInfo>>

    suspend fun insertListOfCompanies(companies: List<Expertise>, gistId: String)

}