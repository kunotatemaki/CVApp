package com.raul.androidapps.cvapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.cvapp.persistence.entities.EducationEntity


@Dao
abstract class EducationDao : BaseDao<EducationEntity>() {

    @Query("SELECT description FROM education_table WHERE gist_id LIKE :gistId ORDER BY position DESC")
    abstract fun getEducation(gistId: String): LiveData<List<String>>

    @Query("DELETE FROM education_table WHERE gist_id LIKE :gistId AND position > :lastPosition")
    abstract suspend fun removeOutdatedEducation(gistId: String, lastPosition: Int)

}