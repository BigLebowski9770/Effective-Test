package com.loves2spooge.effectivetest.di

import com.loves2spooge.effectivetest.di.modules.NetworkModule
import com.loves2spooge.effectivetest.presetnation.activity.MainActivity
import com.loves2spooge.feature_calatog.di.CatalogDeps
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Component(modules = [NetworkModule::class])
interface AppComponent : CatalogDeps {

    override val retrofit: Retrofit
    override val client: OkHttpClient

    fun inject(activity: MainActivity)
}