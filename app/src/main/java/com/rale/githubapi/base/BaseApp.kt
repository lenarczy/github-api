package com.rale.githubapi.base

import android.app.Application
import com.rale.githubapi.BuildConfig
import timber.log.Timber

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}