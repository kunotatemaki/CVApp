package com.raul.androidapps.cvapp.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "task_table", indices = [(Index(value = arrayOf("task_id"), unique = true))])
data class TaskEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "task_id")
    val taskId: String,
    @ColumnInfo(name = "gist_id")
    var gistId: String,
    @ColumnInfo(name = "company_id")
    var companyId: Int,
    @ColumnInfo(name = "position")
    var position: Int,
    @ColumnInfo(name = "task")
    var task: String
) {


    companion object {

        fun fromStringTask(task: String, gistId: String, companyId: Int, position: Int): TaskEntity =
            TaskEntity(
                taskId = getTaskId(gistId, companyId, position),
                gistId = gistId,
                companyId = companyId,
                position = position,
                task = task
            )

        fun getTaskId(gistId: String, companyId: Int, position: Int): String = "${gistId}_${companyId}_$position"

    }
}

