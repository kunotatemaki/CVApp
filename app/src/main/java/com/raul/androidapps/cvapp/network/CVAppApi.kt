package com.raul.androidapps.cvapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import java.util.*

interface CVAppApi {

    @GET("gist/{gist_id}")
    suspend fun foo(
        @Header("gist_id") gistId: String
    ): Response<Objects>


}
