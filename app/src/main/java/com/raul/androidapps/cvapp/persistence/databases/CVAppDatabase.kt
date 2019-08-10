package com.raul.androidapps.cvapp.persistence.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raul.androidapps.cvapp.persistence.daos.UserInfoDao
import com.raul.androidapps.cvapp.persistence.entities.UserInfoEntity
import com.raul.androidapps.cvapp.persistence.utils.DbConverters
import com.raul.androidapps.cvapp.persistence.utils.PersistenceConstants

@Database(entities = [(UserInfoEntity::class)], exportSchema = false, version = 1)
@TypeConverters(DbConverters::class)
abstract class CVAppDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao

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
