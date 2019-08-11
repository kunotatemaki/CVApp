package com.raul.androidapps.cvapp.model.responses

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.raul.androidapps.cvapp.model.Miscellaneous


data class MiscellaneousContent constructor(
    val content: String
) {
    fun toListOfMiscellaneousItems(): List<Miscellaneous> =
        try {
            Gson().fromJson<ArrayList<Miscellaneous>>(
                content,
                object : TypeToken<ArrayList<Miscellaneous>>() {}.type
            )
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            listOf()
        }

}