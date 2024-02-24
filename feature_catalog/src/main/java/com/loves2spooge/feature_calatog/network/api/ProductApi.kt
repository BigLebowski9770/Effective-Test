package com.loves2spooge.feature_calatog.network.api

import com.loves2spooge.feature_calatog.network.data.Products
import retrofit2.http.GET

interface ProductApi {

    @GET("97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getProducts(): Products
}