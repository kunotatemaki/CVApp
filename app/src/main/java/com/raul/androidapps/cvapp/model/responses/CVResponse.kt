package com.raul.androidapps.cvapp.model.responses

import com.google.gson.annotations.SerializedName


data class CVResponse constructor(
    @SerializedName("files")
    val files: CVFiles
) {
    data class CVFiles constructor(
        @SerializedName("Education.json")
        val educationContent: EducationContent,
        @SerializedName("Profile.json")
        val profileContent: ProfileContent,
        @SerializedName("Expertise.json")
        val expertiseContent: ExpertiseContent,
        @SerializedName("Skills.json")
        val skillContent: SkillContent,
        @SerializedName("Miscellaneous.json")
        val miscellaneousContent: MiscellaneousContent
    )
}
