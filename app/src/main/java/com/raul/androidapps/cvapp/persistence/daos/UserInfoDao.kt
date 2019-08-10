package com.raul.androidapps.cvapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.cvapp.model.Profile
import com.raul.androidapps.cvapp.persistence.entities.UserInfoEntity


@Dao
abstract class UserInfoDao : BaseDao<UserInfoEntity>() {

    @Query("SELECT * FROM user_info WHERE gist_id LIKE :gistId ")
    abstract fun getUserInfo(gistId: String): LiveData<Profile>

}