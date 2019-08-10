package com.raul.androidapps.cvapp.network

import com.raul.androidapps.cvapp.model.responses.CVResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CVAppApi {

    @GET("gists/{gist_id}")
    suspend fun getCVInfo(
        @Path("gist_id") gistId: String
    ): Response<CVResponse>


}

