package com.loves2spooge.feature_calatog.network.data

data class Price(
    val discount: Int,
    val price: String,
    val priceWithDiscount: String,
    val unit: String
)