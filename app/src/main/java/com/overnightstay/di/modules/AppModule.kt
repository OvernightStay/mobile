package com.overnightstay.di.modules

import com.overnightstay.domain.usecases.GetPlayerFromApiUseCase
import com.overnightstay.domain.usecases.GetPlayerFromPrefUseCase
import com.overnightstay.domain.usecases.LoginUseCase
import com.overnightstay.domain.usecases.RegisterUseCase
import com.overnightstay.domain.usecases.UpdatePlayerOnApiUseCase
import com.overnightstay.view.auth.AuthViewModel
import com.overnightstay.view.book.contents.ContentsOfBookViewModel
import com.overnightstay.view.choose_pers.ChoosePersViewModel
import com.overnightstay.view.congr.CongrViewModel
import com.overnightstay.view.house_of_distribution.HouseOfDistributionViewModel
import com.overnightstay.view.house_of_distribution.finishminigame.FinishGameMemoDistrViewModel
import com.overnightstay.view.house_of_distribution.minigame.GameMemoDistrViewModel
import com.overnightstay.view.night_bus.finishminigame.FinishGameNightBusViewModel
import com.overnightstay.view.night_bus.minigame.GameNightBusViewModel
import com.overnightstay.view.reg.RegViewModel
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

    @Provides
    fun provideContentsOfBookViewModelFactory(

        ) = ContentsOfBookViewModel.Factory(
    )

    @Provides
    fun provideGameNightBusViewModelFactory(

        ) = GameNightBusViewModel.Factory(
    )

    @Provides
    fun provideFinishGameNightBusViewModelFactory(

        ) = FinishGameNightBusViewModel.Factory(
    )

    @Provides
    fun provideHouseOfDistributionViewModelFactory(

        ) = HouseOfDistributionViewModel.Factory(
    )

    @Provides
    fun provideGameMemoDistrViewModelFactory(

        ) = GameMemoDistrViewModel.Factory(
    )

    @Provides
    fun provideFinishGameMemoDistrViewModelFactory(

        ) = FinishGameMemoDistrViewModel.Factory(
    )
}
