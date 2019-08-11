package com.raul.androidapps.cvapp

import com.raul.androidapps.cvapp.model.Expertise
import com.raul.androidapps.cvapp.model.Miscellaneous
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.entities.*
import org.junit.Assert.assertEquals
import org.junit.Test


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
        val companyId = 0
        val position = 0

        val entity = TaskEntity.fromStringTask(
            gistId = gist,
            task = task,
            position = position,
            companyId = companyId
        )
        assertEquals(entity.task, task)
        assertEquals(entity.companyId, companyId)
        assertEquals(entity.gistId, gist)
        assertEquals(entity.position, position)
        assertEquals(
            entity.taskId,
            TaskEntity.getTaskId(
                gistId = gist,
                companyId = companyId,
                position = position
            )
        )
    }

    @Test
    fun entityFromAchievement() {
        val gist = "gist"
        val achievement = "achievement"
        val companyId = 0
        val position = 0

        val entity = AchievementEntity.fromStringAchievement(
            gistId = gist,
            achievement = achievement,
            position = position,
            companyId = companyId
        )
        assertEquals(entity.achievement, achievement)
        assertEquals(entity.companyId, companyId)
        assertEquals(entity.gistId, gist)
        assertEquals(entity.position, position)
        assertEquals(
            entity.achievementId,
            AchievementEntity.getAchievementId(
                gistId = gist,
                companyId = companyId,
                position = position
            )
        )
    }

    @Test
    fun entityFromExpertise() {
        val gist = "gist"
        val expertise = Expertise(
            id = 1,
            tasks = listOf("t1", "t2"),
            achievements = listOf("a1", "a2"),
            company = "name",
            date = "2016-Today"
        )

        val entity = CompanyEntity.fromExpertise(gistId = gist, expertise = expertise)
        assertEquals(entity.name, expertise.company)
        assertEquals(entity.date, expertise.date)
        assertEquals(entity.companyId, expertise.id)
        assertEquals(entity.gistId, gist)
    }

    @Test
    fun entityFromEducation() {
        val gist = "gist"
        val education = "university"
        val position = 0

        val entity =
            EducationEntity.fromEducation(education = education, position = position, gistId = gist)

        assertEquals(entity.position, position)
        assertEquals(entity.description, education)
        assertEquals(entity.gistId, gist)
    }

    @Test
    fun entityFromSkill() {
        val gist = "gist"
        val skill = "Android"
        val position = 0

        val entity = SkillsEntity.fromSkill(skill = skill, position = position, gistId = gist)

        assertEquals(entity.position, position)
        assertEquals(entity.description, skill)
        assertEquals(entity.gistId, gist)
    }

    @Test
    fun entityFromMiscellaneous() {
        val gist = "gist"
        val title = "languages"
        val position = 0
        val id = 1

        val miscellaneous = Miscellaneous(
            id = id,
            title = title,
            value = listOf()
        )
        val entity = MiscellaneousEntity.fromMiscellaneous(
            miscellaneous = miscellaneous,
            position = position,
            gistId = gist
        )

        assertEquals(entity.position, position)
        assertEquals(entity.title, title)
        assertEquals(entity.gistId, gist)
        assertEquals(entity.miscellaneousId, MiscellaneousEntity.getMiscellaneusId(gist, id))
    }

    @Test
    fun entityFromMiscellaneousValue() {
        val miscellaneousId = "miscellaneousId"
        val position = 0

        val miscellaneous = "Spanish"
        val entity = MiscellaneousValueEntity.fromMiscellaneous(
            value = miscellaneous,
            position = position,
            parentId = miscellaneousId
        )

        assertEquals(entity.position, position)
        assertEquals(entity.value, miscellaneous)
        assertEquals(entity.parentId, miscellaneousId)
        assertEquals(entity.miscellaneousValueId, MiscellaneousValueEntity.getMiscellaneousValueId(miscellaneousId, position))
    }

}
