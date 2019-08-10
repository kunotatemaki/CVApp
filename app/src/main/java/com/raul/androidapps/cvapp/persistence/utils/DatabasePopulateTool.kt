package com.raul.androidapps.cvapp.persistence.utils

import com.raul.androidapps.cvapp.persistence.daos.FooDao
import com.raul.androidapps.cvapp.preferences.PreferencesManager
import com.raul.androidapps.cvapp.utils.AssetFileUtil
import javax.inject.Inject

class DatabasePopulateTool @Inject constructor(
    private val assetFileUtil: AssetFileUtil,
    private val preferencesManager: PreferencesManager
) {

    suspend fun populateDb(dao: FooDao) {

    }

}