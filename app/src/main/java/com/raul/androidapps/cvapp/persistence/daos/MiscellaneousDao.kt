package com.raul.androidapps.cvapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.raul.androidapps.cvapp.persistence.entities.MiscellaneousEntity
import com.raul.androidapps.cvapp.persistence.relations.MiscellaneousWithAllInfo


@Dao
abstract class MiscellaneousDao : BaseDao<MiscellaneousEntity>() {

    @Transaction
    @Query("SELECT * FROM miscellaneous_table WHERE gist_id LIKE :gistId ORDER BY position ASC")
    abstract fun getListOfMiscellaneous(gistId: String): LiveData<List<MiscellaneousWithAllInfo>>

    @Query("DELETE FROM miscellaneous_table WHERE gist_id LIKE :gistId AND position > :lastPosition")
    abstract suspend fun removeOutdatedMiscellaneous(gistId: String, lastPosition: Int)

}