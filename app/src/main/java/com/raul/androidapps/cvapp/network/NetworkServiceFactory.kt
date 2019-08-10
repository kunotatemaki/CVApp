package com.raul.androidapps.cvapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class NetworkServiceFactory @Inject constructor() {

    companion object {
        private const val BASE_URL = "https://base.url"

    }

    @Volatile
    private var instance: CVAppApi? = null

    fun getServiceInstance(): CVAppApi =
        instance ?: synchronized(this) {
            instance ?: buildNetworkService().also { instance = it }
        }

    private fun buildNetworkService(): CVAppApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(CVAppApi::class.java)


}