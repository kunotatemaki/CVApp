package com.raul.androidapps.cvapp.network


interface NetworkServiceFactory {

    companion object {
        const val BASE_URL = "https://api.github.com"

    }

    fun getServiceInstance(): CVAppApi
}