package com.raul.androidapps.cvapp.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index


@Entity(
    tableName = "skills_table", primaryKeys = ["gist_id", "position"],
    indices = [(Index(value = arrayOf("gist_id")))]
)
data class SkillsEntity constructor(
    @ColumnInfo(name = "gist_id")
    val gistId: String,
    @ColumnInfo(name = "position")
    var position: Int,
    @ColumnInfo(name = "description")
    var description: String
) {

    companion object {

        fun fromSkill(skill: String, gistId: String, position: Int): SkillsEntity =
            SkillsEntity(
                gistId = gistId,
                position = position,
                description = skill
            )

    }
}

