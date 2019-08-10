package com.raul.androidapps.cvapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.daos.UserInfoDao
import com.raul.androidapps.cvapp.persistence.databases.CVAppDatabase
import com.raul.androidapps.cvapp.persistence.entities.UserInfoEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDBTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: CVAppDatabase
    private lateinit var userInfoDao: UserInfoDao

    @Before
    @Throws(Exception::class)
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            CVAppDatabase::class.java
        ).allowMainThreadQueries().build() // allowing main thread queries, just for testing

        userInfoDao = database.userInfoDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        database.close()
    }


    @Test
    @Throws(InterruptedException::class)
    fun getUserInfoNull() {
        runBlocking {
            val info = userInfoDao.getUserInfo("").getItem()
            assertNull(info)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun getOneUser() {
        runBlocking {
            val gist1 = "gist1"
            val userInfo1 = Profile(
                github = "github1",
                linkedin = "linkedin1",
                email = "email1",
                phone = "phone1",
                name = "name1",
                description = "description1"
                )
            val gist2 = "gist2"
            val userInfo2 = Profile(
                github = "github2",
                linkedin = "linkedin2",
                email = "email2",
                phone = "phone2",
                name = "name2",
                description = "description2"
                )
            val userEntity1 = UserInfoEntity.fromProfile(profile = userInfo1, gistId = gist1)
            val userEntity2 = UserInfoEntity.fromProfile(profile = userInfo2, gistId = gist2)
            userInfoDao.insert(listOf(userEntity1, userEntity2))
            val userStored1 = userInfoDao.getUserInfo(gist1).getItem()
            val userStored2 = userInfoDao.getUserInfo(gist2).getItem()
            assertEquals(userStored1, userInfo1)
            assertEquals(userStored2, userInfo2)
        }
    }

}