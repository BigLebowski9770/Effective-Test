package com.loves2spooge.effectivetest

import android.app.Application
import com.loves2spooge.effectivetest.di.AppComponent
import com.loves2spooge.effectivetest.di.DaggerAppComponent
import com.loves2spooge.feature_calatog.di.CatalogDepsStore

class App : Application() {

    val appComponent: AppComponent by lazy {
    DaggerAppComponent.builder()
        .build()
}

    override fun onCreate() {
        super.onCreate()
        CatalogDepsStore.deps = appComponent
    }
}
