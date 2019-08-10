package com.raul.androidapps.cvapp.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.raul.androidapps.cvapp.model.Profile


@Entity(tableName = "user_info", indices = [(Index(value = arrayOf("gist_id"), unique = true))])
data class UserInfoEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "gist_id")
    val gistId: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "phone")
    var phone: String,
    @ColumnInfo(name = "linkedin")
    var linkedin: String,
    @ColumnInfo(name = "github")
    var github: String,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "description")
    var description: String
){

    fun toProfile(): Profile =
        Profile(
            name = this.name,
            phone = this.phone,
            linkedin = this.linkedin,
            github = this.github,
            email = this.email,
            description = this.description
        )
    companion object{

        fun fromProfile(profile: Profile, gistId: String): UserInfoEntity =
            UserInfoEntity(
                gistId = gistId,
                name = profile.name,
                phone = profile.phone,
                linkedin = profile.linkedin,
                github = profile.github,
                email = profile.email,
                description = profile.description
            )

    }
}

