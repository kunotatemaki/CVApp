package com.raul.androidapps.cvapp.model.responses

import com.google.gson.annotations.SerializedName
import com.raul.androidapps.cvapp.model.responses.EducationContent


data class CVResponse constructor(
    @SerializedName("files")
    val files: CVFiles
){
    data class CVFiles constructor(
        @SerializedName("Education.json")
        val educationContent: EducationContent,
        @SerializedName("Profile.json")
        val profileContent: ProfileContent
    )
}
