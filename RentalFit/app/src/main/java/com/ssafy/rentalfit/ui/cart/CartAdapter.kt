package com.ssafy.rentalfit.ui.cart

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.databinding.ListCartItemBinding

class CartAdapter(): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    lateinit var cartDeleteListener: ItemClickListener
    lateinit var cartMinusListener: ItemClickListener
    lateinit var cartPlusListener: ItemClickListener

    interface ItemClickListener {
        fun onClick()
    }

    inner class CartViewHolder(private val binding: ListCartItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindInfo(position: Int) {

            binding.apply {

                // 삭제 버튼
                deleteListCartItem.setOnClickListener {
                    cartDeleteListener.onClick()
                }

                // - 버튼
                imageListCartItemMinus.setOnClickListener {
                    cartMinusListener.onClick()
                }

                // + 버튼
                imageListCartItemPlus.setOnClickListener {
                    cartPlusListener.onClick()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ListCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindInfo(position)
    }
}