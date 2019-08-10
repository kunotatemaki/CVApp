package com.raul.androidapps.cvapp.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.raul.androidapps.cvapp.model.Profile


@Entity(tableName = "company_table", primaryKeys = ["gist_id", "company_id"],
    indices = [(Index(value = arrayOf("gist_id")))])
data class CompanyEntity constructor(
    @ColumnInfo(name = "gist_id")
    val gistId: String,
    @ColumnInfo(name = "company_id")
    var companyId: Int,
    @ColumnInfo(name = "name")
    var name: String
){

    companion object{

//        fun fromProfile(profile: Profile, gistId: String): CompanyEntity =
//            CompanyEntity(
//                gistId = gistId,
//                name = profile.name,
//                phone = profile.phone,
//                linkedin = profile.linkedin,
//                github = profile.github,
//                email = profile.email,
//                description = profile.description
//            )

    }
}

