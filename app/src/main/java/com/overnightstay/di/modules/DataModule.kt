package com.overnightstay.di.modules

import com.overnightstay.data.api.UserApi
import com.overnightstay.data.repository.UserRepository
import com.overnightstay.domain.irepository.IUserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideUserRepository(userApi: UserApi) : IUserRepository{
        return UserRepository(userApi = userApi)
    }
}