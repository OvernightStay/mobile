package com.overnightstay.di.modules

import com.overnightstay.data.repository.AuthRepository
import com.overnightstay.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository = repository)
    }
}