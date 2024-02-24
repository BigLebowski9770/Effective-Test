package com.loves2spooge.feature_calatog.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loves2spooge.feature_calatog.network.data.Products
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class CatalogViewModel @Inject constructor(
    private val retrofit: Retrofit,
) : ViewModel() {

    private val _productList: MutableLiveData<Products> = MutableLiveData()
    val productList: LiveData<Products> get() = _productList

//    init {
//        getProduct()
//    }
//
//    fun getProduct() {
//        val productApi = retrofit.create(ProductApi::class.java)
//        viewModelScope.launch {
//            runCatching {
//                productApi.getProducts()
//            }.onSuccess {
//                _productList.postValue(it)
//                Log.e("RequestSuccess", it.toString())
//            }.onFailure {
//                Log.e("RequestException", it.toString())
//            }
//        }
//    }
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