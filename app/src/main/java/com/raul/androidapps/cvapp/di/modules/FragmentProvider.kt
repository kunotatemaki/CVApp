package com.raul.androidapps.cvapp.di.modules

import com.raul.androidapps.cvapp.di.interfaces.CustomScopes
import com.raul.androidapps.cvapp.ui.education.EducationFragment
import com.raul.androidapps.cvapp.ui.expertise.ExpertiseFragment
import com.raul.androidapps.cvapp.ui.info.InfoFragment
import com.raul.androidapps.cvapp.ui.miscellaneous.MiscellaneousFragment
import com.raul.androidapps.cvapp.ui.skill.SkillFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Suppress("unused")
@Module
abstract class FragmentsProvider {

    @CustomScopes.FragmentScope
    @ContributesAndroidInjector
    abstract fun providesMainFragmentFactory(): InfoFragment

    @CustomScopes.FragmentScope
    @ContributesAndroidInjector
    abstract fun providesEducationFragmentFactory(): EducationFragment

    @CustomScopes.FragmentScope
    @ContributesAndroidInjector
    abstract fun providesExpertiseFragmentFactory(): ExpertiseFragment

    @CustomScopes.FragmentScope
    @ContributesAndroidInjector
    abstract fun providesSkillFragmentFactory(): SkillFragment

    @CustomScopes.FragmentScope
    @ContributesAndroidInjector
    abstract fun providesMiscellaneousFragmentFactory(): MiscellaneousFragment

}