package com.raul.androidapps.cvapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raul.androidapps.cvapp.di.interfaces.ViewModelKey
import com.raul.androidapps.cvapp.ui.common.CVAppViewModelFactory
import com.raul.androidapps.cvapp.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap



@Suppress("unused")
@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: CVAppViewModelFactory): ViewModelProvider.Factory
}