package com.raul.androidapps.cvapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.raul.androidapps.cvapp.model.responses.CVResponse
import com.raul.androidapps.cvapp.network.CVAppApi
import com.raul.androidapps.cvapp.network.NetworkServiceFactory
import com.raul.androidapps.cvapp.network.Resource
import com.raul.androidapps.cvapp.persistence.PersistenceManager
import com.raul.androidapps.cvapp.preferences.PreferencesConstants
import com.raul.androidapps.cvapp.preferences.PreferencesManager
import com.raul.androidapps.cvapp.repository.Repository
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response


class RepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var persistenceManager: PersistenceManager

    @Mock
    lateinit var networkServiceFactory: NetworkServiceFactory

    @Mock
    lateinit var preferencesManager: PreferencesManager

    @Mock
    lateinit var api: CVAppApi

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = Repository(networkServiceFactory, preferencesManager, persistenceManager)
    }

    @Test
    fun testShouldFetchFalse() {
        Mockito.`when`(preferencesManager.getLongFromPreferences(PreferencesConstants.PROPERTY_FETCHED_TIMESTAMP))
            .thenReturn(
                System.currentTimeMillis()
            )
        assertEquals(repository.shouldFetch(), false)
    }

    @Test
    fun testShouldFetchTrue() {
        Mockito.`when`(preferencesManager.getLongFromPreferences(PreferencesConstants.PROPERTY_FETCHED_TIMESTAMP))
            .thenReturn(
                System.currentTimeMillis() - 6 * 60 * 1000
            )
        assertEquals(repository.shouldFetch(), true)
    }

    @Test
    fun testFetchReturnsError() {
        runBlocking {
            val gistId = "gistId"
            mockErrorResponse(gistId)
            repository.fetchFromNetwork(gistId, true)
            val value = repository.getLoadingState().getItemFromLiveData()
            assertEquals(value.status, Resource.Status.ERROR)
        }
    }

    @Test
    fun testFetchReturnsSuccess() {
        runBlocking {
            val gistId = "gistId"
            mockSuccessResponse(gistId)
            repository.fetchFromNetwork(gistId, true)
            val value = repository.getLoadingState().getItemFromLiveData()
            assertEquals(value.status, Resource.Status.SUCCESS)
        }
    }

    @Test
    fun testGetUserInfoLoadsFromDb() {
        runBlocking {
            val gistId = "gistId"
            mockErrorResponse(gistId)
            repository.getUserInfo(gistId)
            Mockito.verify(persistenceManager).getUserInfo(gistId)
        }

    }

    @Test
    fun testGetSkillsInfoLoadsFromDb() {
        runBlocking {
            val gistId = "gistId"
            mockErrorResponse(gistId)
            repository.getSkills(gistId)
            Mockito.verify(persistenceManager).getListOfSkills(gistId)
        }

    }

    @Test
    fun testGetEducationInfoLoadsFromDb() {
        runBlocking {
            val gistId = "gistId"
            mockErrorResponse(gistId)
            repository.getEducation(gistId)
            Mockito.verify(persistenceManager).getEducation(gistId)
        }

    }

    @Test
    fun testGetMiscellaneousInfoLoadsFromDb() {
        runBlocking {
            val gistId = "gistId"
            mockErrorResponse(gistId)
            repository.getMiscellaneous(gistId)
            Mockito.verify(persistenceManager).getListOfMiscellaneous(gistId)
        }

    }
//todo expertise
//    @Test
//    fun testGetUserInfoLoadsFromDb() {
//        runBlocking {
//            val gistId = "gistId"
//            mockErrorResponse(gistId)
//            repository.getUserInfo(gistId)
//            Mockito.verify(persistenceManager).getUserInfo(gistId)
//        }
//
//    }

    private suspend fun mockErrorResponse(gistId: String){
        Mockito.`when`(preferencesManager.getLongFromPreferences(PreferencesConstants.PROPERTY_FETCHED_TIMESTAMP))
            .thenReturn(
                System.currentTimeMillis()
            )
        Mockito.`when`(networkServiceFactory.getServiceInstance()).thenReturn(
            api
        )
        Mockito.`when`(api.getCVInfo(gistId)).thenReturn(
            Response.error(400, "".toResponseBody(null))
        )
    }

    private suspend fun mockSuccessResponse(gistId: String){
        Mockito.`when`(preferencesManager.getLongFromPreferences(PreferencesConstants.PROPERTY_FETCHED_TIMESTAMP))
            .thenReturn(
                System.currentTimeMillis()
            )
        Mockito.`when`(networkServiceFactory.getServiceInstance()).thenReturn(
            api
        )
        Mockito.`when`(api.getCVInfo(gistId)).thenReturn(
            Response.success(CVResponse(null))
        )
    }

}
