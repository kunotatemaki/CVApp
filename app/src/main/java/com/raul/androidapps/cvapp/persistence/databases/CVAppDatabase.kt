package com.raul.androidapps.cvapp.persistence.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raul.androidapps.cvapp.persistence.daos.AchievementDao
import com.raul.androidapps.cvapp.persistence.daos.CompanyDao
import com.raul.androidapps.cvapp.persistence.daos.TaskDao
import com.raul.androidapps.cvapp.persistence.daos.UserInfoDao
import com.raul.androidapps.cvapp.persistence.entities.AchievementEntity
import com.raul.androidapps.cvapp.persistence.entities.CompanyEntity
import com.raul.androidapps.cvapp.persistence.entities.TaskEntity
import com.raul.androidapps.cvapp.persistence.entities.UserInfoEntity
import com.raul.androidapps.cvapp.persistence.utils.DbConverters
import com.raul.androidapps.cvapp.persistence.utils.PersistenceConstants

@Database(entities = [(UserInfoEntity::class), (TaskEntity::class),
    (AchievementEntity::class), (CompanyEntity::class)], exportSchema = false, version = 1)
@TypeConverters(DbConverters::class)
abstract class CVAppDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
    abstract fun taskDao(): TaskDao
    abstract fun achievementDao(): AchievementDao
    abstract fun companyDao(): CompanyDao

    companion object {

        @Volatile
        private var INSTANCE: CVAppDatabase? = null

        fun getInstance(
            context: Context
        ): CVAppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(
            context: Context
        ) =
            Room.databaseBuilder(
                context,
                CVAppDatabase::class.java, PersistenceConstants.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()

    }
}
