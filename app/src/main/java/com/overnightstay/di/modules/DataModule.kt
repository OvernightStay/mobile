package com.overnightstay.di.modules

import android.content.SharedPreferences
import com.overnightstay.data.api.UserApi
import com.overnightstay.data.repository.UserRepository
import com.overnightstay.data.storage.UserStorage
import com.overnightstay.domain.irepository.IUserRepository
import com.overnightstay.domain.istorage.IUserStorage
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

    @Provides
    @Singleton
    fun provideUserStorage(sharedPreferences: SharedPreferences) : IUserStorage{
        return UserStorage(sharedPreferences = sharedPreferences)
    }
}