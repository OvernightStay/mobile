package com.overnightstay.di.modules

import com.overnightstay.view.MainActivity
import com.overnightstay.view.auth.AuthFragment
import com.overnightstay.view.backpack.BackpackFragment
import com.overnightstay.view.book.contents.ContentsOfBookFragment
import com.overnightstay.view.book.history.HistoryFragment
import com.overnightstay.view.book.projects.ProjectsFragment
import com.overnightstay.view.book.projects.house_of_distribution.ProjectHouseOfDistributionFragment
import com.overnightstay.view.book.projects.house_rehabilitation.ProjectHouseRehabilitationFragment
import com.overnightstay.view.book.projects.house_shower.ProjectHouseShowerFragment
import com.overnightstay.view.book.projects.house_warm.ProjectHouseWarmFragment
import com.overnightstay.view.book.projects.night_bus.ProjectNightBusFragment
import com.overnightstay.view.choose_pers.ChoosePersFragment
import com.overnightstay.view.congr.CongrFragment
import com.overnightstay.view.dating.DatingFragment
import com.overnightstay.view.house_of_distribution.HouseOfDistributionFragment
import com.overnightstay.view.house_shower.DialogInTheBuilding2Fragment
import com.overnightstay.view.location_map.LocationMapFragment
import com.overnightstay.view.night_bus.minigame.GameToFeedTheNeedyFragment
import com.overnightstay.view.house_shower.DialogInTheBuildingFragment
import com.overnightstay.view.house_shower.DialogOnTheStreetFragment
import com.overnightstay.view.night_bus.NightBusFragment
import com.overnightstay.view.night_bus.NightBusTrainingFragment
import com.overnightstay.view.night_bus.finishminigame.FinishGameNightBusFragment
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
    @ContributesAndroidInjector
    fun bindDatingFragment(): DatingFragment
    @ContributesAndroidInjector
    fun bindNightBusFragment(): NightBusFragment
    @ContributesAndroidInjector
    fun bindNightBusTrainingFragment(): NightBusTrainingFragment
    @ContributesAndroidInjector
    fun bindLocationMapFragment(): LocationMapFragment
    @ContributesAndroidInjector
    fun bindGameToFeedTheNeedyFragment(): GameToFeedTheNeedyFragment
    @ContributesAndroidInjector
    fun bindDialogOnTheStreetFragment(): DialogOnTheStreetFragment
    @ContributesAndroidInjector
    fun bindDialogInTheBuildingFragment(): DialogInTheBuildingFragment
    @ContributesAndroidInjector
    fun bindContentsOfBookFragment(): ContentsOfBookFragment
    @ContributesAndroidInjector
    fun bindOverNightStayProjectsFragment(): HistoryFragment
    @ContributesAndroidInjector
    fun bindProjectsFragment(): ProjectsFragment
    @ContributesAndroidInjector
    fun bindProjectNightBusFragment(): ProjectNightBusFragment
    @ContributesAndroidInjector
    fun bindProjectHouseShowerFragment(): ProjectHouseShowerFragment
    @ContributesAndroidInjector
    fun bindProjectHouseRehabilitationFragment(): ProjectHouseRehabilitationFragment
    @ContributesAndroidInjector
    fun bindProjectHouseOfDistributionFragment(): ProjectHouseOfDistributionFragment
    @ContributesAndroidInjector
    fun bindProjectHouseWarmFragment(): ProjectHouseWarmFragment
    @ContributesAndroidInjector
    fun bindBackpackFragment(): BackpackFragment
    @ContributesAndroidInjector
    fun bindFinishGameNightBusFragment(): FinishGameNightBusFragment
    @ContributesAndroidInjector
    fun bindDialogInTheBuilding2Fragment(): DialogInTheBuilding2Fragment
    @ContributesAndroidInjector
    fun bindHouseOfDistributionFragment(): HouseOfDistributionFragment

}