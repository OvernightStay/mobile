package com.overnightstay.di.modules

import com.overnightstay.domain.irepository.IUserRepository
import com.overnightstay.domain.usecases.LoginUseCase
import com.overnightstay.domain.usecases.RegisterUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideLoginUseCase(repository: IUserRepository): LoginUseCase {
        return LoginUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideRegisterUser(repository: IUserRepository): RegisterUseCase {
        return RegisterUseCase(repository = repository)
    }
}