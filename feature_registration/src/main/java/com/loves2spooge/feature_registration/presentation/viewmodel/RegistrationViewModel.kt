package com.loves2spooge.feature_registration.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class RegistrationViewModel @Inject constructor(): ViewModel()

class RegistrationViewModelFactory @Inject constructor(
    viewModelProvider: Provider<RegistrationViewModel>
) : ViewModelProvider.Factory {
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        RegistrationViewModel::class.java to viewModelProvider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}