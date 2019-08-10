package com.raul.androidapps.cvapp.di.modules

import android.content.Context
import com.raul.androidapps.softwaretesttandem.persistence.databases.CVAppDatabase
import com.raul.androidapps.cvapp.CVAppApplication
import com.raul.androidapps.cvapp.persistence.utils.DatabasePopulateTool
import com.raul.androidapps.cvapp.preferences.PreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
class CVAppProvidesModule {


    @Provides
    fun providesContext(application: CVAppApplication): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideDb(
        context: Context,
        preferencesManager: PreferencesManager,
        databasePopulateTool: DatabasePopulateTool
    ): CVAppDatabase = CVAppDatabase.getInstance(context, preferencesManager, databasePopulateTool)


}