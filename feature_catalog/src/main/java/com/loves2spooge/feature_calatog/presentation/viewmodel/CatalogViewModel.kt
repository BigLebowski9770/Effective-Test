package com.loves2spooge.feature_calatog.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.loves2spooge.feature_calatog.network.api.ProductApi
import com.loves2spooge.feature_calatog.network.data.Products
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class CatalogViewModel @Inject constructor(
    val retrofit: Retrofit,
) : ViewModel() {

    private val _productList: MutableLiveData<Products> = MutableLiveData()
    val productList: LiveData<Products> = _productList

    init {
        val productApi = retrofit.create(ProductApi::class.java)
        viewModelScope.launch {
            val result = productApi.getProducts()
            _productList.value = result
            Log.d("TAG", result.toString())
        }
    }
}

class CatalogViewModelFactory @Inject constructor(
    viewModelProvider: Provider<CatalogViewModel>
) : ViewModelProvider.Factory {
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        CatalogViewModel::class.java to viewModelProvider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}