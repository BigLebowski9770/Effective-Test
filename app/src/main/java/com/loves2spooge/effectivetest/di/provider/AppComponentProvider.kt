package com.loves2spooge.effectivetest.di.provider

import com.loves2spooge.effectivetest.di.AppComponent

interface AppComponentProvider {
    fun provideAppComponent(): AppComponent
}