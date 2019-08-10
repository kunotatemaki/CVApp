package com.raul.androidapps.cvapp.model.responses

import com.google.gson.Gson
import com.raul.androidapps.cvapp.model.Profile


data class ProfileContent constructor(
    val content: String
){
    fun toProfile(): Profile? =

            Gson().fromJson(content, Profile::class.java)


}