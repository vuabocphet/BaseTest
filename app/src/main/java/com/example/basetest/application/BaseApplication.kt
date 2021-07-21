package com.example.basetest.application

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.basetest.dagger.ApplicationComponent
import com.example.basetest.dagger.DaggerApplicationComponent

abstract class BaseApplication : Application() {

    open lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        if (!this::appComponent.isInitialized) {
            this.appComponent = DaggerApplicationComponent.create()
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}