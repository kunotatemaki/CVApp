package com.raul.androidapps.cvapp.network

import com.raul.androidapps.cvapp.network.NetworkServiceFactory.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkServiceFactoryImpl @Inject constructor(): NetworkServiceFactory {

    @Volatile
    private var instance: CVAppApi? = null

    override fun getServiceInstance(): CVAppApi =
        instance ?: buildNetworkService().also { instance = it }

    private fun buildNetworkService(): CVAppApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(CVAppApi::class.java)


}