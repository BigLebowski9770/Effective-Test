package com.loves2spooge.feature_registration.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loves2spooge.feature_registration.data.UserData
import javax.inject.Inject
import javax.inject.Provider

class RegistrationViewModel @Inject constructor(): ViewModel() {

    private val userData = MutableLiveData<UserData>()

    fun setUserData(userFirstName: String, userLastName: String, userPhoneNumber: String) {
        val data = UserData(userFirstName, userLastName, userPhoneNumber)
        userData.value = data
    }

    fun getUserData(): LiveData<UserData> {
        return userData
    }
}

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