package com.loves2spooge.feature_calatog.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // Логирование ответа сервера
        Log.d("Response", response.body?.string()!!)

        return response
    }
}