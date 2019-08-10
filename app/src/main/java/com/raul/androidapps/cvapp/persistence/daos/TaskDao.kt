package com.raul.androidapps.cvapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.entities.TaskEntity
import com.raul.androidapps.cvapp.persistence.entities.UserInfoEntity


@Dao
abstract class TaskDao : BaseDao<TaskEntity>() {

    @Query("SELECT task FROM task_table WHERE gist_id LIKE :gistId ORDER BY position ASC")
    abstract fun getListOfTasks(gistId: String): LiveData<List<String>>

    @Query("DELETE FROM task_table WHERE gist_id LIKE :gistId AND position > :lastPosition")
    abstract fun removeListOfTasks(gistId: String, lastPosition: Int)

}