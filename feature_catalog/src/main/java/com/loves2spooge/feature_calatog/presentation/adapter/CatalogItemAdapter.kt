package com.loves2spooge.feature_calatog.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.loves2spooge.feature_calatog.databinding.ProductItemBinding
import com.loves2spooge.feature_calatog.network.data.Item

class CatalogItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}

class CatalogItemAdapter :
    ListAdapter<Item, CatalogItemAdapter.ViewHolder>(CatalogItemDiffCallback()) {

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Item) = with(binding) {
            imageProduct.setImageResource(com.loves2spooge.core.R.drawable.product_1)
            textOldPrice.text = product.price.price
            textPriceWithDiscount.text = product.price.priceWithDiscount
            textPriceDiscount.text = product.price.discount.toString()
            textTitle.text = product.title
            textSubtitle.text = product.subtitle
            textFeedbackRating.text = product.feedback.rating.toString()
            textFeedbackCount.text = product.feedback.count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fileItem = getItem(position)
        holder.bind(fileItem)
    }

}
