package com.overnightstay.di.modules

import com.overnightstay.data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideAuthRepository() = AuthRepository()
}