package com.overnightstay.di.modules

import com.overnightstay.domain.usecases.GetPlayerFromApiUseCase
import com.overnightstay.domain.usecases.GetPlayerFromPrefUseCase
import com.overnightstay.domain.usecases.LoginUseCase
import com.overnightstay.domain.usecases.RegisterUseCase
import com.overnightstay.domain.usecases.UpdatePlayerOnApiUseCase
import com.overnightstay.view.auth.AuthViewModel
import com.overnightstay.view.choose_pers.ChoosePersViewModel
import com.overnightstay.view.congr.CongrViewModel
import com.overnightstay.view.reg.RegViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule() {
    @Provides
    fun provideAuthViewModelFactory(
        loginUseCase: LoginUseCase,
        getPlayerFromApiUseCase: GetPlayerFromApiUseCase,
    ) = AuthViewModel.Factory(
        loginUseCase = loginUseCase,
        getPlayerFromApiUseCase = getPlayerFromApiUseCase
    )

    @Provides
    fun provideRegisterViewModelFactory(
        registerUseCase: RegisterUseCase,

        ) = RegViewModel.Factory(
        registerUseCase = registerUseCase
    )

    @Provides
    fun provideCongrViewModelFactory(
        loginUseCase: LoginUseCase,

        ) = CongrViewModel.Factory(
        loginUseCase = loginUseCase,
    )

    @Provides
    fun provideChoosePersViewModelFactory(
        getPlayerFromPrefUseCase: GetPlayerFromPrefUseCase,
        updatePlayerOnApiUseCase: UpdatePlayerOnApiUseCase,

        ) = ChoosePersViewModel.Factory(
        getPlayerFromPrefUseCase = getPlayerFromPrefUseCase,
        updatePlayerOnApiUseCase = updatePlayerOnApiUseCase,
    )

}
