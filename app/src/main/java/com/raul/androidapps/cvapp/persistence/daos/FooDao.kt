package com.raul.androidapps.cvapp.persistence.daos

import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.cvapp.persistence.entities.FooEntity


@Dao
abstract class FooDao : BaseDao<FooEntity>() {

    @Query("SELECT * FROM foo")
    abstract suspend fun getFoo(): List<FooEntity>


}