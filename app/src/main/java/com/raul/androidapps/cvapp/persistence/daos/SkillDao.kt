package com.raul.androidapps.cvapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.cvapp.persistence.entities.SkillsEntity


@Dao
abstract class SkillDao : BaseDao<SkillsEntity>() {

    @Query("SELECT description FROM skills_table WHERE gist_id LIKE :gistId ORDER BY position DESC")
    abstract fun getListOfSkills(gistId: String): LiveData<List<String>>

    @Query("DELETE FROM skills_table WHERE gist_id LIKE :gistId AND position > :lastPosition")
    abstract suspend fun removeOutdatedSkills(gistId: String, lastPosition: Int)

}