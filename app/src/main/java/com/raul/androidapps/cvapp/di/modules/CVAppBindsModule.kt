package com.raul.androidapps.cvapp.di.modules

import com.raul.androidapps.cvapp.network.NetworkServiceFactory
import com.raul.androidapps.cvapp.network.NetworkServiceFactoryImpl
import com.raul.androidapps.cvapp.persistence.PersistenceManager
import com.raul.androidapps.cvapp.persistence.PersistenceManagerImpl
import com.raul.androidapps.cvapp.preferences.PreferencesManager
import com.raul.androidapps.cvapp.preferences.PreferencesManagerImpl
import com.raul.androidapps.cvapp.resources.ResourcesManager
import com.raul.androidapps.cvapp.resources.ResourcesManagerImpl
import com.raul.androidapps.cvapp.security.Encryption
import com.raul.androidapps.cvapp.security.EncryptionImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module(includes = [(ViewModelModule::class)])
abstract class CVAppBindsModule {


    @Binds
    abstract fun provideResourcesManager(resourcesManagerImpl: ResourcesManagerImpl): ResourcesManager

    @Binds
    abstract fun providePersistenceManager(persistenceManagerImpl: PersistenceManagerImpl): PersistenceManager

    @Binds
    abstract fun provideNetworkServiceFactory(networkServiceFactoryImp: NetworkServiceFactoryImpl): NetworkServiceFactory

    @Binds
    abstract fun provideEncryption(encryptionImpl: EncryptionImpl): Encryption

    @Binds
    abstract fun providePreferencesManager(preferencesManagerImpl: PreferencesManagerImpl): PreferencesManager
}