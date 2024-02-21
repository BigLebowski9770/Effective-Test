package com.loves2spooge.feature_calatog.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.loves2spooge.feature_calatog.R
import com.loves2spooge.feature_calatog.databinding.ProductItemBinding
import com.loves2spooge.feature_calatog.network.data.Item

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    val productList = ArrayList<Item>()

    class ProductHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ProductItemBinding.bind(item)
        fun bind(product: Item) = with(binding) {
            imageProduct.setImageResource(com.loves2spooge.core.R.drawable.product_1)
            textOldPrice.text = product.price.toString()
            textPriceWithDiscount.text = product.price.priceWithDiscount
            textPriceDiscount.text = product.price.discount.toString()
            textTitle.text = product.title
            textSubtitle.text = product.subtitle
            textFeedbackRating.text = product.feedback.rating.toString()
            textFeedbackCount.text = product.feedback.count.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item, parent, false)

        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class DiffUserCallBack : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }
}
