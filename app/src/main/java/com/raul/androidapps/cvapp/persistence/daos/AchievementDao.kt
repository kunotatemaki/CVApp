package com.raul.androidapps.cvapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.cvapp.persistence.entities.AchievementEntity


@Dao
abstract class AchievementDao : BaseDao<AchievementEntity>() {

    @Query("SELECT achievement FROM achievement_table WHERE gist_id LIKE :gistId AND parent_id LIKE :parentId ORDER BY position ASC")
    abstract fun getListOfAchievements(gistId: String, parentId: String): LiveData<List<String>>

    @Query("DELETE FROM achievement_table WHERE gist_id LIKE :gistId AND parent_id LIKE :parentId AND position > :lastPosition")
    abstract suspend fun removeListOfAchievement(gistId: String, parentId: String, lastPosition: Int)

}