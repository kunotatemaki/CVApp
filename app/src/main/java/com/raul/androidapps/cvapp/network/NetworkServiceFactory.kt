package com.raul.androidapps.cvapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkServiceFactory @Inject constructor() {

    companion object {
        private const val BASE_URL = "https://api.github.com"

    }

    @Volatile
    private var instance: CVAppApi? = null

    fun getServiceInstance(): CVAppApi =
        instance ?: buildNetworkService().also { instance = it }

    private fun buildNetworkService(): CVAppApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(CVAppApi::class.java)


}