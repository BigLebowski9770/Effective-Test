package com.loves2spooge.feature_registration.di

import com.loves2spooge.feature_registration.presentation.fragmen.RegistrationFragment
import dagger.Component

@Component
interface RegistrationComponent {

    fun inject(fragment: RegistrationFragment)

    @Component.Builder
    interface Builder {

        fun build(): RegistrationComponent
    }
}