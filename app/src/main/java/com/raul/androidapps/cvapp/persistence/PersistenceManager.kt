package com.raul.androidapps.cvapp.persistence

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.model.Expertise
import com.raul.androidapps.cvapp.model.Miscellaneous
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.relations.CompanyWithAllInfo
import com.raul.androidapps.cvapp.persistence.relations.MiscellaneousWithAllInfo

interface PersistenceManager {

    fun getUserInfo(gistId: String): LiveData<Profile>

    suspend fun insertUserInfo(profile: Profile, gistId: String)

    fun getListOfTasks(gistId: String, companyId: Int): LiveData<List<String>>

    suspend fun insertListOfTasks(
        tasks: List<String>,
        gistId: String,
        companyId: Int
    )

    fun getListOfAchievements(gistId: String, companyId: Int): LiveData<List<String>>

    suspend fun insertListOfAchievements(
        achievements: List<String>,
        gistId: String,
        companyId: Int
    )

    fun getListOfCompanies(gistId: String): LiveData<List<CompanyWithAllInfo>>

    suspend fun insertListOfCompanies(companies: List<Expertise>, gistId: String)

    fun getEducation(gistId: String): LiveData<List<String>>

    suspend fun insertEducation(
        educationList: List<String>,
        gistId: String
    )

    fun getListOfSkills(gistId: String): LiveData<List<String>>

    suspend fun insertListOfSkills(
        skillList: List<String>,
        gistId: String
    )

    fun getListOfMiscellaneous(gistId: String): LiveData<List<MiscellaneousWithAllInfo>>

    suspend fun insertListOfMiscellaneous(miscellaneousList: List<Miscellaneous>, gistId: String)




}