package com.raul.androidapps.cvapp.model


data class Expertise constructor(
    val id: Int,
    val tasks: List<String>,
    val achievements: List<String>,
    val company: String,
    val date: String
)