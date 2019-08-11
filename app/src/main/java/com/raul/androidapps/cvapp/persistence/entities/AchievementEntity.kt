package com.raul.androidapps.cvapp.persistence.entities

import androidx.room.*


@Entity(
    foreignKeys = [ForeignKey(
        entity = CompanyEntity::class,
        parentColumns = arrayOf("company_id"),
        childColumns = arrayOf("parent_id"),
        onDelete = ForeignKey.CASCADE
    )],
    tableName = "achievement_table",
    indices = [(Index(value = arrayOf("achievement_id"), unique = true))]
)
data class AchievementEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "achievement_id")
    val achievementId: String,
    @ColumnInfo(name = "gist_id")
    var gistId: String,
    @ColumnInfo(name = "parent_id")
    var parentId: String,
    @ColumnInfo(name = "position")
    var position: Int,
    @ColumnInfo(name = "achievement")
    var achievement: String
) {

    companion object {

        fun fromStringAchievement(
            achievement: String,
            gistId: String,
            companyId: Int,
            position: Int
        ): AchievementEntity =
            AchievementEntity(
                achievementId = getAchievementId(CompanyEntity.getCompanyId(gistId, companyId), position),
                gistId = gistId,
                parentId = CompanyEntity.getCompanyId(gistId, companyId),
                position = position,
                achievement = achievement
            )

        fun getAchievementId(parentId: String, position: Int): String =
            "${parentId}_$position"
    }
}

