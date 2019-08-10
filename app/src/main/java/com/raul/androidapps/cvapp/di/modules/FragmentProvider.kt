package com.raul.androidapps.cvapp.di.modules

import com.raul.androidapps.cvapp.di.interfaces.CustomScopes
import com.raul.androidapps.cvapp.ui.main.InfoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Suppress("unused")
@Module
abstract class FragmentsProvider {

    @CustomScopes.FragmentScope
    @ContributesAndroidInjector
    abstract fun providesMainFragmentFactory(): InfoFragment

}