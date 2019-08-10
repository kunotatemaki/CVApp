package com.raul.androidapps.cvapp

import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.entities.UserInfoEntity
import org.junit.Test

import org.junit.Assert.*


class EntitiesTest {
    @Test
    fun entityFromProfile() {
        val gist = "gist"
        val profile = Profile(
            github = "github",
            linkedin = "linkedin",
            email = "email",
            phone = "phone",
            name = "name",
            description = "description"
        )
        val entity = UserInfoEntity.fromProfile(profile = profile, gistId = gist)
        assertEquals(profile.description, entity.description)
        assertEquals(profile.github, entity.github)
        assertEquals(profile.email, entity.email)
        assertEquals(profile.linkedin, entity.linkedin)
        assertEquals(profile.name, entity.name)
    }
}
