package com.raul.androidapps.cvapp

import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.entities.AchievementEntity
import com.raul.androidapps.cvapp.persistence.entities.TaskEntity
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

    @Test
    fun entityFromTask() {
        val gist = "gist"
        val task = "task"

        val entity = TaskEntity.fromStringTask(gistId = gist, task = task, position = 0)
        assertEquals(entity.task, task)
    }

    @Test
    fun entityFromAchievement() {
        val gist = "gist"
        val achievement = "achievement"

        val entity = AchievementEntity.fromStringAchievement(gistId = gist, achievement = achievement, position = 0)
        assertEquals(entity.achievement, achievement)
    }

}
