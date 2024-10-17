package com.overnightstay.di.modules

import com.overnightstay.view.MainActivity
import com.overnightstay.view.auth.AuthFragment
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
import com.overnightstay.view.location_map.LocationMapFragment
import com.overnightstay.view.night_bus.minigame.GameToFeedTheNeedyFragment
import com.overnightstay.view.house_shower.DialogInTheBuildingFragment
import com.overnightstay.view.house_shower.DialogOnTheStreetFragment
import com.overnightstay.view.night_bus.NightBusFragment
import com.overnightstay.view.night_bus.NightBusTrainingFragment
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
    fun bindGameToFeedTheNeedy(): GameToFeedTheNeedyFragment
    @ContributesAndroidInjector
    fun bindDialogOnTheStreet(): DialogOnTheStreetFragment
    @ContributesAndroidInjector
    fun bindDialogInTheBuilding(): DialogInTheBuildingFragment
    @ContributesAndroidInjector
    fun bindContentsOfBookFragment(): ContentsOfBookFragment
    @ContributesAndroidInjector
    fun bindOverNightStayProjectsFragment(): HistoryFragment
    @ContributesAndroidInjector
    fun bindProjectsFragment(): ProjectsFragment
    @ContributesAndroidInjector
    fun bindProjectNightBus(): ProjectNightBusFragment
    @ContributesAndroidInjector
    fun bindProjectHouseShower(): ProjectHouseShowerFragment
    @ContributesAndroidInjector
    fun bindProjectHouseRehabilitation(): ProjectHouseRehabilitationFragment
    @ContributesAndroidInjector
    fun bindProjectHouseOfDistribution(): ProjectHouseOfDistributionFragment
    @ContributesAndroidInjector
    fun bindProjectHouseWarm(): ProjectHouseWarmFragment

}