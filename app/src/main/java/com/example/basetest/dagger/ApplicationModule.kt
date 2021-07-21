package com.example.basetest.dagger

import android.content.Context
import com.example.basetest.application.BaseApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(var application: BaseApplication) {
    @Provides
    @ApplicationScope
    fun provideApplicationScope(): Context {
        return application
    }
}