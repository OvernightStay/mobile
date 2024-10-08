package com.overnightstay.di.modules

import com.overnightstay.domain.irepository.IUserRepository
import com.overnightstay.domain.istorage.IUserStorage
import com.overnightstay.domain.usecases.LoginUseCase
import com.overnightstay.domain.usecases.RegisterUseCase
import com.overnightstay.domain.usecases.SaveUserToSharedPrefUseCase
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

//    @Singleton
//    @Provides
//    fun provideRegisterUser(repository: IUserRepository, storage: IUserStorage): RegisterUseCase {
//        return RegisterUseCase(repository = repository, storage = storage)
//    }

    @Singleton
    @Provides
    fun provideRegisterUser(repository: IUserRepository, saveUser: SaveUserToSharedPrefUseCase): RegisterUseCase {
        return RegisterUseCase(repository = repository, saveUserToStorage = saveUser)
    }

    @Singleton
    @Provides
    fun provideSaveUserToSharedPrefUseCase(storage: IUserStorage): SaveUserToSharedPrefUseCase {
        return SaveUserToSharedPrefUseCase(storage = storage)
    }
}