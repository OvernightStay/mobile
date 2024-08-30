package com.overnightstay.di

import android.content.Context
import com.overnightstay.App
import com.overnightstay.di.modules.DomainModule
import com.overnightstay.di.modules.MainModule
import com.overnightstay.di.modules.MappersModule
import com.overnightstay.di.modules.RemoteModule
import com.overnightstay.di.modules.DataModule
import com.overnightstay.di.modules.DatabaseModule
import com.overnightstay.di.modules.AppModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        AndroidInjectionModule::class,
        MainModule::class,
        AppModule::class,
        DatabaseModule::class,
        DataModule::class,
        DomainModule::class,
        MappersModule::class,
        RemoteModule::class,
    ]
)

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder
        fun build(): AppComponent
    }
}

