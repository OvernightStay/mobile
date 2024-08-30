package com.overnightstay.di.modules

import dagger.Module

@Module
class RemoteModule {


    companion object {
        private const val HALF_MINUTE_FOR_SLOW_INTERNET = 30L
        const val BASE_URL = "https://grikoandrey.pythonanywhere.com/"
    }
}