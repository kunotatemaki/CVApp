package com.raul.androidapps.cvapp.di.modules

import com.raul.androidapps.cvapp.resources.ResourcesManager
import com.raul.androidapps.cvapp.resources.ResourcesManagerImpl
import dagger.Binds
import dagger.Module


@Module(includes = [(ViewModelModule::class)])
abstract class CVAppBindsModule {


    @Binds
    abstract fun provideResourcesManager(resourcesManagerImpl: ResourcesManagerImpl): ResourcesManager

}