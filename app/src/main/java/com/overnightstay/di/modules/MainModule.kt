package com.overnightstay.di.modules

import com.overnightstay.view.MainActivity
import com.overnightstay.view.auth.AuthFragment
import com.overnightstay.view.choose_pers.ChoosePersFragment
import com.overnightstay.view.congr.CongrFragment
import com.overnightstay.view.onboarding.OnboardingFragment
import com.overnightstay.view.reg.RegFragment
import com.overnightstay.view.restore.RestoreFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
    @ContributesAndroidInjector
    fun bindOnboardingFragment(): OnboardingFragment
    @ContributesAndroidInjector
    fun bindAuthFragment(): AuthFragment
    @ContributesAndroidInjector
    fun bindChoosePersFragment(): ChoosePersFragment
    @ContributesAndroidInjector
    fun bindCongrFragment(): CongrFragment
    @ContributesAndroidInjector
    fun bindRegFragment(): RegFragment
    @ContributesAndroidInjector
    fun bindRestoreFragment(): RestoreFragment

}