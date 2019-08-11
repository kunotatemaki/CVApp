package com.raul.androidapps.cvapp.persistence

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.model.Expertise
import com.raul.androidapps.cvapp.model.Miscellaneous
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.databases.CVAppDatabase
import com.raul.androidapps.cvapp.persistence.entities.*
import com.raul.androidapps.cvapp.persistence.relations.CompanyWithAllInfo
import com.raul.androidapps.cvapp.persistence.relations.MiscellaneousWithAllInfo
import javax.inject.Inject

class PersistenceManagerImpl @Inject constructor(
    private val db: CVAppDatabase
) : PersistenceManager {

    override fun getUserInfo(gistId: String): LiveData<Profile> =
        db.userInfoDao().getUserInfo(gistId)

    override suspend fun insertUserInfo(profile: Profile, gistId: String) {
        db.userInfoDao().insert(UserInfoEntity.fromProfile(profile, gistId))
    }

    override fun getListOfTasks(gistId: String, companyId: Int): LiveData<List<String>> =
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

    override fun getListOfAchievements(
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

    override fun getListOfCompanies(gistId: String): LiveData<List<CompanyWithAllInfo>> =
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

    override fun getEducation(gistId: String): LiveData<List<String>> =
        db.educationDao().getEducation(gistId)

    override suspend fun insertEducation(educationList: List<String>, gistId: String) {
        val educationEntities = educationList.mapIndexed { index, education ->
            EducationEntity.fromEducation(
                gistId = gistId,
                position = index,
                education = education
            )
        }
        db.educationDao().insert(educationEntities)
        removeOutdatedEducation(gistId, educationList.lastIndex)
    }

    private suspend fun removeOutdatedEducation(gistId: String, lastPosition: Int) {
        db.educationDao().removeOutdatedEducation(gistId, lastPosition)
    }

    override fun getListOfSkills(gistId: String): LiveData<List<String>> =
        db.skillDao().getListOfSkills(gistId)

    override suspend fun insertListOfSkills(skillList: List<String>, gistId: String) {
        val skillEntities = skillList.mapIndexed { index, skill ->
            SkillsEntity.fromSkill(
                gistId = gistId,
                position = index,
                skill = skill
            )
        }
        db.skillDao().insert(skillEntities)
        removeOutdatedSkills(gistId, skillList.lastIndex)
    }

    private suspend fun removeOutdatedSkills(gistId: String, lastPosition: Int) {
        db.skillDao().removeOutdatedSkills(gistId, lastPosition)
    }

    override fun getListOfMiscellaneous(gistId: String): LiveData<List<MiscellaneousWithAllInfo>> =
        db.miscellaneousDao().getListOfMiscellaneous(gistId)

    override suspend fun insertListOfMiscellaneous(
        miscellaneousList: List<Miscellaneous>,
        gistId: String
    ) {
        val listOfValues: MutableList<MiscellaneousValueEntity> = mutableListOf()
        val listOfMiscellaneous = miscellaneousList.mapIndexed { index, miscellaneous ->
            val entity = MiscellaneousEntity.fromMiscellaneous(miscellaneous, gistId, index)
            miscellaneous.value.forEachIndexed { innerIndex, value ->
                listOfValues.add(
                    MiscellaneousValueEntity.fromMiscellaneous(
                        value, entity.miscellaneousId, innerIndex
                    )
                )
            }
            removeOutdatedMiscellaneousValues(entity.miscellaneousId, miscellaneous.value.lastIndex)
            entity
        }
        db.miscellaneousDao().insert(listOfMiscellaneous)
        db.miscellaneousValueDao().insert(listOfValues)
        removeOutdatedMiscellaneous(gistId, miscellaneousList.lastIndex)
    }

    private suspend fun removeOutdatedMiscellaneous(gistId: String, lastPosition: Int) {
        db.miscellaneousDao().removeOutdatedMiscellaneous(gistId, lastPosition)
    }

    private suspend fun removeOutdatedMiscellaneousValues(parentId: String, lastPosition: Int) {
        db.miscellaneousValueDao().removeOutdatedMiscellaneousValues(parentId, lastPosition)
    }


}

