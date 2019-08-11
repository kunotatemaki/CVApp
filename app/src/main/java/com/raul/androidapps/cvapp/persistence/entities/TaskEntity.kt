package com.raul.androidapps.cvapp.persistence.entities

import androidx.room.*


@Entity(
    foreignKeys = [ForeignKey(
        entity = CompanyEntity::class,
        parentColumns = arrayOf("company_id"),
        childColumns = arrayOf("parent_id"),
        onDelete = ForeignKey.CASCADE
    )],
    tableName = "task_table", indices = [(Index(value = arrayOf("task_id"), unique = true))]
)
data class TaskEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "task_id")
    val taskId: String,
    @ColumnInfo(name = "gist_id")
    var gistId: String,
    @ColumnInfo(name = "parent_id")
    var parentId: String,
    @ColumnInfo(name = "position")
    var position: Int,
    @ColumnInfo(name = "task")
    var task: String
) {

    companion object {

        fun fromStringTask(
            task: String,
            gistId: String,
            companyId: Int,
            position: Int
        ): TaskEntity =
            TaskEntity(
                taskId = getTaskId(CompanyEntity.getCompanyId(gistId, companyId), position),
                gistId = gistId,
                parentId = CompanyEntity.getCompanyId(gistId, companyId),
                position = position,
                task = task
            )

        fun getTaskId(parentId: String, position: Int): String =
            "${parentId}_$position"

    }
}

