package com.raul.androidapps.cvapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.cvapp.persistence.entities.AchievementEntity


@Dao
abstract class AchievementDao : BaseDao<AchievementEntity>() {

    @Query("SELECT achievement FROM achievement_table WHERE gist_id LIKE :gistId AND company_id = :companyId ORDER BY position ASC")
    abstract fun getListOfAchievements(gistId: String, companyId: Int): LiveData<List<String>>

    @Query("DELETE FROM achievement_table WHERE gist_id LIKE :gistId AND company_id = :companyId AND position > :lastPosition")
    abstract fun removeListOfAchievement(gistId: String, companyId: Int, lastPosition: Int)

}