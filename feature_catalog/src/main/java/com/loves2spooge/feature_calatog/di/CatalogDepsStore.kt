package com.loves2spooge.feature_calatog.di

import kotlin.properties.Delegates

object CatalogDepsStore : CatalogDepsProvider {

    override var deps: CatalogDeps by Delegates.notNull()
}