package com.loves2spooge.feature_calatog.di

import com.loves2spooge.feature_calatog.presentation.fragment.CatalogFragment
import dagger.Component

@Component(dependencies = [CatalogDeps::class])
internal interface CatalogComponent {

    fun inject(fragment: CatalogFragment)

    @Component.Builder
    interface Builder {

        fun deps(deps: CatalogDeps): Builder

        fun build(): CatalogComponent
    }
}