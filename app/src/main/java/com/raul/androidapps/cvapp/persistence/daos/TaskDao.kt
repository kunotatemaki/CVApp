package com.raul.androidapps.cvapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.cvapp.persistence.entities.TaskEntity



@Dao
abstract class TaskDao : BaseDao<TaskEntity>() {

    @Query("SELECT task FROM task_table WHERE gist_id LIKE :gistId AND parent_id LIKE :parentId ORDER BY position ASC")
    abstract fun getListOfTasks(gistId: String, parentId: String): LiveData<List<String>>

    @Query("DELETE FROM task_table WHERE gist_id LIKE :gistId AND parent_id LIKE :parentId AND position > :lastPosition")
    abstract suspend fun removeListOfTasks(gistId: String, parentId: String, lastPosition: Int)

}