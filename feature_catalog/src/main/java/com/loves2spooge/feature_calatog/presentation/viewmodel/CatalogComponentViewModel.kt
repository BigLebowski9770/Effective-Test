package com.loves2spooge.feature_calatog.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.loves2spooge.feature_calatog.di.CatalogDepsProvider
import com.loves2spooge.feature_calatog.di.DaggerCatalogComponent

internal class CatalogComponentViewModel : ViewModel() {

    val catalogComponent =
        DaggerCatalogComponent.builder().deps(CatalogDepsProvider.deps).build()
}