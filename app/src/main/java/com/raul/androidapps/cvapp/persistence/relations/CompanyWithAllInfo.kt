package com.raul.androidapps.cvapp.persistence.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.raul.androidapps.cvapp.persistence.entities.AchievementEntity
import com.raul.androidapps.cvapp.persistence.entities.CompanyEntity
import com.raul.androidapps.cvapp.persistence.entities.TaskEntity


class CompanyWithAllInfo {
    @Embedded
    lateinit var company: CompanyEntity

    @Relation(parentColumn = "company_id", entityColumn = "parent_id")
    lateinit var tasks: List<TaskEntity>

    @Relation(parentColumn = "company_id", entityColumn = "parent_id")
    lateinit var achievements: List<AchievementEntity>

}