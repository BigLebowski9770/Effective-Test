package com.loves2spooge.feature_calatog.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.loves2spooge.feature_calatog.R
import com.loves2spooge.feature_calatog.presentation.viewmodel.CatalogComponentViewModel

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private lateinit var sortButton: AppCompatButton

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CatalogComponentViewModel>()
            .catalogComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        showSortMenu()

    }

    private fun setUi() {
        sortButton = requireActivity().findViewById(R.id.sortButton)
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
                // Скрывать выпадающий список при закрытии
            }

            popupMenu.show()
        }
    }
}
