package com.loves2spooge.feature_calatog.di

import androidx.annotation.RestrictTo

interface CatalogDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: CatalogDeps

    companion object : CatalogDepsProvider by CatalogDepsStore
}
