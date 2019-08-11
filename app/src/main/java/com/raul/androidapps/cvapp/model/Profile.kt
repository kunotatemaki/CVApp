package com.raul.androidapps.cvapp.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName


data class Profile constructor(
    val name: String,
    val email: String,
    val phone: String,
    val linkedin: String,
    val github: String,
    @SerializedName("profile_pic")
    @ColumnInfo(name = "profile_pic")
    val profilePic: String,
    @SerializedName("background_pic")
    @ColumnInfo(name = "background_pic")
    val backgroundPic: String,
    val description: String
)