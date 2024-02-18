package com.loves2spooge.effectivetest

import android.app.Application
import com.loves2spooge.effectivetest.di.AppComponent
import com.loves2spooge.effectivetest.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}