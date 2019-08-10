package com.raul.androidapps.cvapp.model.responses

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken


data class EducationContent constructor(
    val content: String
) {
    fun toListOfEducationItems(): List<String> =
        try{
            Gson().fromJson<ArrayList<String>>(content, object : TypeToken<ArrayList<String>>() {}.type)
        }catch (e: JsonSyntaxException){
            listOf()
        }

}