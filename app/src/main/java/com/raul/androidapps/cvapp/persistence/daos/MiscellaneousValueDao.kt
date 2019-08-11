package com.raul.androidapps.cvapp.persistence.daos

import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.cvapp.persistence.entities.MiscellaneousValueEntity


@Dao
abstract class MiscellaneousValueDao : BaseDao<MiscellaneousValueEntity>() {

    @Query("SELECT value FROM miscellaneous_value_table WHERE parent_id LIKE :parentId ")
    abstract suspend fun getListOfMiscellaneous(parentId: String): List<String>

    @Query("DELETE FROM miscellaneous_value_table WHERE parent_id LIKE :parent_id AND position > :lastPosition")
    abstract suspend fun removeOutdatedMiscellaneousValues(parent_id: String, lastPosition: Int)

}