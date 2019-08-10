package com.raul.androidapps.cvapp.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "achievement_table", indices = [(Index(value = arrayOf("achievement_id"), unique = true))])
data class AchievementEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "achievement_id")
    val achievementId: String,
    @ColumnInfo(name = "gist_id")
    var gistId: String,
    @ColumnInfo(name = "company_id")
    var companyId: Int,
    @ColumnInfo(name = "position")
    var position: Int,
    @ColumnInfo(name = "achievement")
    var achievement: String
) {


    companion object {

        fun fromStringAchievement(achievement: String, gistId: String, companyId: Int, position: Int): AchievementEntity =
            AchievementEntity(
                achievementId = getAchievementId(gistId, companyId, position),
                gistId = gistId,
                companyId = companyId,
                position = position,
                achievement = achievement
            )

        fun getAchievementId(gistId: String, companyId: Int, position: Int): String = "${gistId}_${companyId}_$position"
    }
}

