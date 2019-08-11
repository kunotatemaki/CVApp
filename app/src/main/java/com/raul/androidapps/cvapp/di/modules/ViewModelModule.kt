package com.raul.androidapps.cvapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raul.androidapps.cvapp.di.interfaces.ViewModelKey
import com.raul.androidapps.cvapp.ui.MainViewModel
import com.raul.androidapps.cvapp.ui.common.CVAppViewModelFactory
import com.raul.androidapps.cvapp.ui.education.EducationViewModel
import com.raul.androidapps.cvapp.ui.expertise.ExpertiseViewModel
import com.raul.androidapps.cvapp.ui.info.InfoViewModel
import com.raul.androidapps.cvapp.ui.miscellaneous.MiscellaneousViewModel
import com.raul.androidapps.cvapp.ui.skill.SkillViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap



@Suppress("unused")
@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(InfoViewModel::class)
    internal abstract fun bindInfoViewModel(infoViewModel: InfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpertiseViewModel::class)
    internal abstract fun bindExpertiseViewModel(expertiseViewModel: ExpertiseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SkillViewModel::class)
    internal abstract fun bindSkillViewModel(skillViewModel: SkillViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EducationViewModel::class)
    internal abstract fun bindEducationViewModel(educationViewModel: EducationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MiscellaneousViewModel::class)
    internal abstract fun bindMiscellaneousViewModel(miscellaneousViewModel: MiscellaneousViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: CVAppViewModelFactory): ViewModelProvider.Factory
}