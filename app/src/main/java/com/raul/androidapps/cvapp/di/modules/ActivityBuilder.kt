package com.raul.androidapps.cvapp.di.modules

import com.raul.androidapps.cvapp.di.interfaces.CustomScopes
import com.raul.androidapps.cvapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuilder {

    @CustomScopes.ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity


}