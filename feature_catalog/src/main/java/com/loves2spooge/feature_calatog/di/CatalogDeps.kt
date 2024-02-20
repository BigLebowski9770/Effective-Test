package com.loves2spooge.feature_calatog.di

import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface CatalogDeps {

    val retrofit: Retrofit
    val client: OkHttpClient
}