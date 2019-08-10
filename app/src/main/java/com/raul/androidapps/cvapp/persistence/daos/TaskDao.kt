package com.raul.androidapps.cvapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.cvapp.persistence.entities.TaskEntity



@Dao
abstract class TaskDao : BaseDao<TaskEntity>() {

    @Query("SELECT task FROM task_table WHERE gist_id LIKE :gistId AND company_id = :companyId ORDER BY position ASC")
    abstract fun getListOfTasks(gistId: String, companyId: Int): LiveData<List<String>>

    @Query("DELETE FROM task_table WHERE gist_id LIKE :gistId AND company_id = :companyId AND position > :lastPosition")
    abstract suspend fun removeListOfTasks(gistId: String, companyId: Int, lastPosition: Int)

}