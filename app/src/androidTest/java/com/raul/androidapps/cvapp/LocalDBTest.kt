package com.raul.androidapps.cvapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.raul.androidapps.cvapp.model.Expertise
import com.raul.androidapps.cvapp.model.Miscellaneous
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.PersistenceManager
import com.raul.androidapps.cvapp.persistence.PersistenceManagerImpl
import com.raul.androidapps.cvapp.persistence.databases.CVAppDatabase
import com.raul.androidapps.cvapp.persistence.entities.CompanyEntity
import com.raul.androidapps.cvapp.persistence.entities.MiscellaneousEntity
import com.raul.androidapps.cvapp.persistence.relations.CompanyWithAllInfo
import com.raul.androidapps.cvapp.persistence.relations.MiscellaneousWithAllInfo
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDBTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: CVAppDatabase
    private lateinit var persistenceManager: PersistenceManager

    @Before
    @Throws(Exception::class)
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            CVAppDatabase::class.java
        ).allowMainThreadQueries().build() // allowing main thread queries, just for testing

        persistenceManager = PersistenceManagerImpl(database)

    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        database.close()
    }


    @Test
    @Throws(InterruptedException::class)
    fun testGetUserInfoNull() {
        runBlocking {
            val info = persistenceManager.getUserInfo("").getItem()
            assertNull(info)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testGetOneUser() {
        runBlocking {
            val gist1 = "gist1"
            val userInfo1 = Profile(
                github = "github1",
                linkedin = "linkedin1",
                email = "email1",
                phone = "phone1",
                name = "name1",
                description = "description1",
                profilePic = "profilePic1",
                backgroundPic = "backgroundPic1"
            )
            val gist2 = "gist2"
            val userInfo2 = Profile(
                github = "github2",
                linkedin = "linkedin2",
                email = "email2",
                phone = "phone2",
                name = "name2",
                description = "description2",
                profilePic = "profilePic2",
                backgroundPic = "backgroundPic2"
            )
            persistenceManager.insertUserInfo(userInfo1, gist1)
            persistenceManager.insertUserInfo(userInfo2, gist2)

            val userStored1 = persistenceManager.getUserInfo(gist1).getItem()
            val userStored2 = persistenceManager.getUserInfo(gist2).getItem()
            assertEquals(userStored1, userInfo1)
            assertEquals(userStored2, userInfo2)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testGetListOfTasksForOneCompanyOrdered() {
        runBlocking {
            val gist = "gist1"
            val companyId1 = 1
            val tasksCompany1 =
                listOf("task${companyId1}_1", "task${companyId1}_2", "task${companyId1}_3")
            val companyId2 = 2
            val tasksCompany2 =
                listOf("task${companyId2}_1", "task${companyId2}_2", "task${companyId2}_3")

            val expertise1 = Expertise(
                id = companyId1,
                achievements = listOf(),
                tasks = tasksCompany1,
                date = "date",
                company = "name"
            )
            val expertise2 = Expertise(
                id = companyId2,
                achievements = listOf(),
                tasks = tasksCompany2,
                date = "date",
                company = "name"
            )
            persistenceManager.insertListOfCompanies(listOf(expertise1, expertise2), gist)

            val tasksStored: List<String> =
                persistenceManager.getListOfTasks(
                    gist,
                    CompanyEntity.getCompanyId(gist, companyId1)
                ).getItem()

            assertEquals(tasksCompany1, tasksStored)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testRemoveExtraTasks() {
        runBlocking {
            val gist = "gist"
            val companyId = 1
            val initialTasks = listOf("task1", "task2", "task3")

            val expertiseBefore = Expertise(
                id = companyId,
                achievements = listOf(),
                tasks = initialTasks,
                date = "date",
                company = "name"
            )

            persistenceManager.insertListOfCompanies(listOf(expertiseBefore), gist)

            val updatedTasks = listOf("updatedTask1", "updatedTask2")
            val expertiseAfter = Expertise(
                id = companyId,
                achievements = listOf(),
                tasks = updatedTasks,
                date = "date",
                company = "name"
            )
            persistenceManager.insertListOfCompanies(listOf(expertiseAfter), gist)

            val tasksStored: List<String> =
                persistenceManager.getListOfTasks(gist, CompanyEntity.getCompanyId(gist, companyId))
                    .getItem()

            assertEquals(updatedTasks, tasksStored)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testGetListOfAchievementsForOneCompanyOrdered() {
        runBlocking {
            val gist = "gist1"
            val companyId1 = 1
            val achievementsCompany1 =
                listOf(
                    "achievement${companyId1}_1",
                    "achievement${companyId1}_2",
                    "achievement${companyId1}_3"
                )
            val companyId2 = 2
            val achievementsCompany2 =
                listOf(
                    "achievement${companyId2}_1",
                    "achievement${companyId2}_2",
                    "achievement${companyId2}_3"
                )

            val expertise1 = Expertise(
                id = companyId1,
                tasks = listOf(),
                achievements = achievementsCompany1,
                date = "date",
                company = "name"
            )
            val expertise2 = Expertise(
                id = companyId2,
                tasks = listOf(),
                achievements = achievementsCompany2,
                date = "date",
                company = "name"
            )
            persistenceManager.insertListOfCompanies(listOf(expertise1, expertise2), gist)

            val achievementsStored: List<String> =
                persistenceManager.getListOfAchievements(
                    gist,
                    CompanyEntity.getCompanyId(gist, companyId1)
                ).getItem()

            assertEquals(achievementsCompany1, achievementsStored)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testRemoveExtraAchievements() {
        runBlocking {
            val gist = "gist"
            val companyId = 1
            val initialAchievements = listOf("achievement1", "achievement2", "achievement3")
            val expertiseBefore = Expertise(
                id = companyId,
                tasks = listOf(),
                achievements = initialAchievements,
                date = "date",
                company = "name"
            )

            persistenceManager.insertListOfCompanies(listOf(expertiseBefore), gist)

            val updatedAchievements = listOf("updatedAchievement1", "updatedAchievement2")
            val expertiseAfter = Expertise(
                id = companyId,
                tasks = listOf(),
                achievements = updatedAchievements,
                date = "date",
                company = "name"
            )
            persistenceManager.insertListOfCompanies(listOf(expertiseAfter), gist)

            val achievementsStored: List<String> =
                persistenceManager.getListOfAchievements(
                    gist,
                    CompanyEntity.getCompanyId(gist, companyId)
                ).getItem()

            assertEquals(updatedAchievements, achievementsStored)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testDeleteCompanyWithValuesInCascade() {
        runBlocking {
            val gist = "gist"
            val companyId1 = 1
            val companyId2 = 2
            val achievementsCompany =
                listOf(
                    "achievement_1",
                    "achievement_2",
                    "achievement_3"
                )
            val tasksCompany =
                listOf("task_1", "task_2", "task_3")

            val expertise1 = Expertise(
                id = companyId1,
                tasks = tasksCompany,
                achievements = achievementsCompany,
                date = "date",
                company = "name"
            )
            val expertise2 = Expertise(
                id = companyId2,
                tasks = tasksCompany,
                achievements = achievementsCompany,
                date = "date",
                company = "name"
            )


            persistenceManager.insertListOfCompanies(
                listOf(expertise1, expertise2),
                gist
            )

            val tasksStoredBeforeCascade: List<String> =
                persistenceManager.getListOfTasks(
                    gist,
                    CompanyEntity.getCompanyId(gist, companyId2)
                ).getItem()
            val achievementsStoredBeforeCascade: List<String> =
                persistenceManager.getListOfTasks(
                    gist,
                    CompanyEntity.getCompanyId(gist, companyId2)
                ).getItem()
            assertEquals(tasksStoredBeforeCascade.size, 3)
            assertEquals(achievementsStoredBeforeCascade.size, 3)

            val entity = CompanyEntity.fromExpertise(
                expertise = expertise2,
                gistId = gist
            )
            database.companyDao().delete(entity)

            val tasksStoredAfterCascade: List<String> =
                persistenceManager.getListOfTasks(
                    gist,
                    CompanyEntity.getCompanyId(gist, companyId2)
                ).getItem()
            val achievementsStoredAfterCascade: List<String> =
                persistenceManager.getListOfTasks(
                    gist,
                    CompanyEntity.getCompanyId(gist, companyId2)
                ).getItem()
            assertEquals(tasksStoredAfterCascade.size, 0)
            assertEquals(achievementsStoredAfterCascade.size, 0)
        }
    }


    @Test
    @Throws(InterruptedException::class)
    fun testGetListOfCompaniesOrdered() {
        runBlocking {
            val gist = "gist"
            val date = "date"
            val company0 = Expertise(0, listOf(), listOf(), "company0", date)
            val company1 = Expertise(1, listOf(), listOf(), "company1", date)
            persistenceManager.insertListOfCompanies(listOf(company1, company0, company1), gist)

            val companiesStored: List<CompanyWithAllInfo> =
                persistenceManager.getListOfCompanies(gist).getItem()

            assertEquals(companiesStored.size, 2)
            assertEquals(companiesStored[0].company.name, company1.company)
            assertEquals(companiesStored[1].company.name, company0.company)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testGetCompanyWithAchievementsAndTasks() {
        runBlocking {
            val gist = "gist"
            val date = "date"
            val companyName = "company"
            val companyId = 0

            val tasksCompany =
                listOf("task${companyId}_1", "task${companyId}_2", "task${companyId}_3")
            val achievementsCompany =
                listOf(
                    "achievement${companyId}_1",
                    "achievement${companyId}_2",
                    "achievement${companyId}_3"
                )
            val company = Expertise(
                id = companyId,
                tasks = tasksCompany,
                achievements = achievementsCompany,
                company = companyName,
                date = date
            )
            persistenceManager.insertListOfCompanies(listOf(company), gist)

            val companiesStored: List<CompanyWithAllInfo> =
                persistenceManager.getListOfCompanies(gist).getItem()

            assertEquals(
                companiesStored.first().achievements.map { it.achievement },
                achievementsCompany
            )
            assertEquals(companiesStored.first().tasks.map { it.task }, tasksCompany)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testGetEducationForOneUserOrdered() {
        runBlocking {
            val gist1 = "gist1"
            val gist2 = "gist2"
            val educationUser1 =
                listOf("education_${gist1}_1", "education_${gist1}_2", "education_${gist1}_3")
            val educationUser2 =
                listOf("education_${gist2}_1", "education_${gist2}_2", "education_${gist2}_3")

            persistenceManager.insertEducation(educationUser1, gist1)
            persistenceManager.insertEducation(educationUser2, gist2)
            val educationStored: List<String> =
                persistenceManager.getEducation(gist1).getItem()

            assertEquals(educationStored, educationUser1.asReversed())
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testRemoveExtraEducation() {
        runBlocking {
            val gist = "gist"
            val initialUser1 =
                listOf("education_${gist}_1", "education_${gist}_2", "education_${gist}_3")
            val initialUser2 =
                listOf("education_${gist}_4", "education_${gist}_5")
            persistenceManager.insertEducation(initialUser1, gist)
            persistenceManager.insertEducation(initialUser2, gist)

            val educationStored: List<String> =
                persistenceManager.getEducation(gist).getItem()

            assertEquals(initialUser2.asReversed(), educationStored)
        }
    }


    @Test
    @Throws(InterruptedException::class)
    fun testGetSkillsForOneUserOrdered() {
        runBlocking {
            val gist1 = "gist1"
            val gist2 = "gist2"
            val skillUser1 =
                listOf("skill_${gist1}_1", "skill_${gist1}_2", "skill_${gist1}_3")
            val skillUser2 =
                listOf("skill_${gist2}_1", "skill_${gist2}_2", "skill_${gist2}_3")

            persistenceManager.insertListOfSkills(skillUser1, gist1)
            persistenceManager.insertListOfSkills(skillUser2, gist2)
            val skillsStored: List<String> =
                persistenceManager.getListOfSkills(gist1).getItem()

            assertEquals(skillsStored, skillUser1)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testRemoveExtraSkills() {
        runBlocking {
            val gist = "gist"
            val initialUser1 =
                listOf("skill_${gist}_1", "skill_${gist}_2", "skill_${gist}_3")
            val initialUser2 =
                listOf("skill_${gist}_4", "skill_${gist}_5")
            persistenceManager.insertListOfSkills(initialUser1, gist)
            persistenceManager.insertListOfSkills(initialUser2, gist)

            val skillStored: List<String> =
                persistenceManager.getListOfSkills(gist).getItem()

            assertEquals(initialUser2, skillStored)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testGetListOfMiscellaneousOrdered() {
        runBlocking {
            val gist = "gist"
            val misc0 = Miscellaneous(0, "language", listOf())
            val misc1 = Miscellaneous(1, "awards", listOf())
            persistenceManager.insertListOfMiscellaneous(listOf(misc1, misc0, misc1), gist)

            val miscellaneousStored: List<MiscellaneousWithAllInfo> =
                persistenceManager.getListOfMiscellaneous(gist).getItem()

            assertEquals(miscellaneousStored.size, 2)
            assertEquals(miscellaneousStored[0].miscellaneous.title, misc0.title)
            assertEquals(miscellaneousStored[1].miscellaneous.title, misc1.title)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testGetMiscellaneousWithValues() {
        runBlocking {
            val gist = "gist"
            val title = "company"
            val id = 1

            val values =
                listOf("value_1", "value_2", "value_3")

            val miscellaneous = Miscellaneous(
                id = id,
                title = title,
                value = values
            )
            persistenceManager.insertListOfMiscellaneous(listOf(miscellaneous), gist)

            val miscellaneousStored: List<MiscellaneousWithAllInfo> =
                persistenceManager.getListOfMiscellaneous(gist).getItem()

            assertEquals(miscellaneousStored.first().miscellaneous.title, title)
            assertEquals(miscellaneousStored.first().values.size, values.size)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testDeleteMiscellaneousWithValuesInCascade() {
        runBlocking {
            val gist = "gist"
            val title = "company"
            val id1 = 1
            val id2 = 2

            val values =
                listOf("value_1", "value_2", "value_3")

            val miscellaneous1 = Miscellaneous(
                id = id1,
                title = title,
                value = values
            )

            val miscellaneous2 = Miscellaneous(
                id = id2,
                title = title,
                value = values
            )
            persistenceManager.insertListOfMiscellaneous(
                listOf(miscellaneous1, miscellaneous2),
                gist
            )

            val valuesStoredBeforeCascade: List<String> = database.miscellaneousValueDao()
                .getListOfMiscellaneous(MiscellaneousEntity.getMiscellaneusId(gist, id2))
            assertEquals(valuesStoredBeforeCascade.size, 3)

            val entityToRemove = MiscellaneousEntity.fromMiscellaneous(miscellaneous2, gist, 1)
            database.miscellaneousDao().delete(entityToRemove)
            val valuesStoredAfterCascade: List<String> = database.miscellaneousValueDao()
                .getListOfMiscellaneous(MiscellaneousEntity.getMiscellaneusId(gist, id2))

            assertEquals(valuesStoredAfterCascade.size, 0)
        }
    }

}