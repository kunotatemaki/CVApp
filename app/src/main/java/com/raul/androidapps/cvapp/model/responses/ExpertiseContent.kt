package com.raul.androidapps.cvapp.model.responses

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.raul.androidapps.cvapp.model.Expertise


data class ExpertiseContent constructor(
    val content: String
) {
    fun toListOfExpertiseItems(): List<Expertise> =

    try{
        Gson().fromJson<ArrayList<Expertise>>(
            content,
            object : TypeToken<ArrayList<Expertise>>() {}.type
        )
    }catch (e: JsonSyntaxException){
        listOf()
    }

}