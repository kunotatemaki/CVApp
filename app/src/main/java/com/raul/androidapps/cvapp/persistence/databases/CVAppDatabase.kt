package com.raul.androidapps.softwaretesttandem.persistence.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.raul.androidapps.cvapp.persistence.utils.DatabasePopulateTool
import com.raul.androidapps.cvapp.persistence.utils.DbConverters
import com.raul.androidapps.cvapp.persistence.utils.PersistenceConstants
import com.raul.androidapps.cvapp.persistence.daos.FooDao
import com.raul.androidapps.cvapp.persistence.entities.FooEntity
import com.raul.androidapps.cvapp.preferences.PreferencesConstants
import com.raul.androidapps.cvapp.preferences.PreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [(FooEntity::class)], exportSchema = false, version = 1)
@TypeConverters(DbConverters::class)
abstract class CVAppDatabase : RoomDatabase() {
    abstract fun fooDao(): FooDao

    companion object {

        @Volatile
        private var INSTANCE: CVAppDatabase? = null

        fun getInstance(
            context: Context,
            preferenceManager: PreferencesManager,
            databasePopulateTool: DatabasePopulateTool
        ): CVAppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context, preferenceManager, databasePopulateTool).also { INSTANCE = it }
            }

        private fun buildDatabase(
            context: Context,
            preferenceManager: PreferencesManager,
            databasePopulateTool: DatabasePopulateTool
        ) =
            Room.databaseBuilder(
                context,
                CVAppDatabase::class.java, PersistenceConstants.DATABASE_NAME
            )
                //.addMigrations()    //no migrations, version 1
                .fallbackToDestructiveMigration()
                // prepopulate the database after onCreate was called
                    //comment if no need to populate
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        if (!preferenceManager.getBooleanFromPreferences(PreferencesConstants.PROPERTY_DB_POPULATED)) {
                            //load fist the small file to get main cities ready earlier (for old devices)
                            GlobalScope.launch(Dispatchers.IO) {
                                databasePopulateTool.populateDb(
                                    getInstance(
                                        context,
                                        preferenceManager,
                                        databasePopulateTool
                                    ).fooDao()
                                )
                            }
                        }
                    }
                })
                .build()

    }
}
