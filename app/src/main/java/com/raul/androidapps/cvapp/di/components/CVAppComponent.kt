package com.raul.androidapps.cvapp.di.components

import com.raul.androidapps.cvapp.CVAppApplication
import com.raul.androidapps.cvapp.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [(AndroidSupportInjectionModule::class), (ActivityBuilder::class), (CVAppBindsModule::class),
        (CVAppProvidesModule::class), (FragmentsProvider::class), (FragmentsProvider::class), (ViewModelModule::class)]
)
interface CVAppComponent : AndroidInjector<CVAppApplication> {

    override fun inject(cvApp: CVAppApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: CVAppApplication): Builder

        fun build(): CVAppComponent
    }

}