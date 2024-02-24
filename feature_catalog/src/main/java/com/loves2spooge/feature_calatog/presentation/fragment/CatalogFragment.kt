package com.loves2spooge.feature_calatog.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.loves2spooge.feature_calatog.R
import com.loves2spooge.feature_calatog.databinding.FragmentCatalogBinding
import com.loves2spooge.feature_calatog.network.api.ProductApi
import com.loves2spooge.feature_calatog.network.data.Item
import com.loves2spooge.feature_calatog.network.data.Products
import com.loves2spooge.feature_calatog.presentation.adapter.CatalogItemAdapter
import com.loves2spooge.feature_calatog.presentation.viewmodel.CatalogComponentViewModel
import com.loves2spooge.feature_calatog.presentation.viewmodel.CatalogViewModel
import com.loves2spooge.feature_calatog.presentation.viewmodel.CatalogViewModelFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private lateinit var sortButton: AppCompatButton

    private lateinit var binding: FragmentCatalogBinding

    @Inject
    lateinit var viewModelFactory: CatalogViewModelFactory

    private val catalogViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CatalogViewModel::class.java]
    }

    @Inject
    lateinit var retrofit: Retrofit

    private lateinit var result: Products
    private lateinit var listResult: List<Item>

    private lateinit var adapter: CatalogItemAdapter

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CatalogComponentViewModel>()
            .catalogComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        showSortMenu()
        getAllItems()
    }

    private fun getAllItems() {
        val productApi = retrofit.create(ProductApi::class.java)

        lifecycleScope.launch {
            runCatching {
                productApi.getProducts()
            }.onSuccess {
                result = it
                listResult = result.items
                setupAdapter(listResult)
                Log.e("RequestSuccess", it.toString())
            }.onFailure {
                Log.e("RequestException", it.toString())
            }
        }
    }

    private fun setupAdapter(list: List<Item>) {
        adapter.submitList(list)
    }

    private fun setUi() {
        sortButton = requireActivity().findViewById(R.id.sortButton)

        binding.apply {
            catalogRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = CatalogItemAdapter()
            catalogRecyclerView.adapter = adapter
        }

    }

    private fun showSortMenu() {
        sortButton.setOnClickListener {

            val popupMenu = PopupMenu(requireActivity(), sortButton)
            popupMenu.menuInflater.inflate(R.menu.sort_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.popularity -> {
                        true
                    }

                    R.id.priceLowToHigh -> {

                        true
                    }

                    R.id.priceHighToLow -> {
                        true
                    }

                    else -> false
                }
            }

            popupMenu.setOnDismissListener {
            }
            popupMenu.show()
        }
    }
}
