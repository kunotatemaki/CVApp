package com.raul.androidapps.cvapp.persistence

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.model.Expertise
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.databases.CVAppDatabase
import com.raul.androidapps.cvapp.persistence.entities.*
import com.raul.androidapps.cvapp.persistence.relations.CompanyWithAllInfo
import javax.inject.Inject

class PersistenceManagerImpl @Inject constructor(
    private val db: CVAppDatabase
) : PersistenceManager {

    override suspend fun getUserInfo(gistId: String): LiveData<Profile> =
        db.userInfoDao().getUserInfo(gistId)

    override suspend fun insertUserInfo(profile: Profile, gistId: String) {
        db.userInfoDao().insert(UserInfoEntity.fromProfile(profile, gistId))
    }

    override suspend fun getListOfTasks(gistId: String, companyId: Int): LiveData<List<String>> =
        db.taskDao().getListOfTasks(gistId, companyId)

    override suspend fun insertListOfTasks(
        tasks: List<String>,
        gistId: String,
        companyId: Int
    ) {
        val listOfTasks = tasks.mapIndexed { index, task ->
            TaskEntity.fromStringTask(
                task = task,
                companyId = companyId,
                gistId = gistId,
                position = index
            )
        }
        db.taskDao().insert(listOfTasks)
        removeListOfTasks(gistId, companyId, listOfTasks.lastIndex)
    }

    private suspend fun removeListOfTasks(gistId: String, companyId: Int, lastPosition: Int) {
        db.taskDao().removeListOfTasks(gistId, companyId, lastPosition)
    }

    override suspend fun getListOfAchievements(
        gistId: String,
        companyId: Int
    ): LiveData<List<String>> =
        db.achievementDao().getListOfAchievements(gistId, companyId)

    override suspend fun insertListOfAchievements(
        achievements: List<String>,
        gistId: String,
        companyId: Int
    ) {
        val listOfAchievements = achievements.mapIndexed { index, achievement ->
            AchievementEntity.fromStringAchievement(
                achievement = achievement,
                companyId = companyId,
                gistId = gistId,
                position = index
            )
        }
        db.achievementDao().insert(listOfAchievements)
        removeListOfAchievements(gistId, companyId, listOfAchievements.lastIndex)
    }

    private suspend fun removeListOfAchievements(
        gistId: String,
        companyId: Int,
        lastPosition: Int
    ) {
        db.achievementDao().removeListOfAchievement(gistId, companyId, lastPosition)
    }

    override suspend fun getListOfCompanies(gistId: String): LiveData<List<CompanyWithAllInfo>> =
        db.companyDao().getListOfCompany(gistId)

    override suspend fun insertListOfCompanies(companies: List<Expertise>, gistId: String) {
        val listOfCompanies = companies.map {
            CompanyEntity.fromExpertise(it, gistId)
        }
        db.companyDao().insert(listOfCompanies)
        companies.forEach {
            insertListOfTasks(it.tasks, gistId, it.id)
            insertListOfAchievements(it.achievements, gistId, it.id)
        }
    }

    override suspend fun getEducation(gistId: String): LiveData<List<String>> =
        db.eduationDao().getEducation(gistId)

    override suspend fun insertEducation(educationList: List<String>, gistId: String) {
        val educationEntities = educationList.mapIndexed { index, education ->
            EducationEntity.fromEducation(
                gistId = gistId,
                position = index,
                education = education
            )
        }
        db.eduationDao().insert(educationEntities)
        removeOutdatedEducation(gistId, educationList.lastIndex)
    }

    private suspend fun removeOutdatedEducation(gistId: String, lastPosition: Int) {
        db.eduationDao().removeOutdatedEducation(gistId, lastPosition)
    }
}

