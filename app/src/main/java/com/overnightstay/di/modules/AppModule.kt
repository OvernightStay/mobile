package com.overnightstay.di.modules

import com.overnightstay.domain.usecases.LoginUseCase
import com.overnightstay.view.auth.AuthViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule() {
    @Provides
    fun provideAuthViewModelFactory(
        loginUseCase: LoginUseCase,
    ) = AuthViewModel.Factory(
        loginUseCase = loginUseCase,
    )

}
