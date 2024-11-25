package com.ssafy.rentalfit.ui.cart

import android.content.ClipData.Item
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.rentalfit.base.ApplicationClass.Companion.SERVER_URL
import com.ssafy.rentalfit.data.model.dto.ShoppingCart
import com.ssafy.rentalfit.databinding.ListCartItemBinding
import com.ssafy.rentalfit.util.Utils.makeComma

private const val TAG = "CartAdapter_싸피"
class CartAdapter(var shoppingList: MutableList<ShoppingCart>): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    lateinit var cartDeleteListener: ItemDeleteListener
    lateinit var cartPlusListener: ItemPlusListener
    lateinit var cartMinusListener: ItemMinusListener

    interface ItemDeleteListener {
        fun onClick(position: Int, shoppingCart: ShoppingCart)
    }

    interface ItemPlusListener {
        fun onPlus(cartName: String)
    }

    interface ItemMinusListener {
        fun onMinus(cartName: String)
    }

    inner class CartViewHolder(private val binding: ListCartItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindInfo(shoppingCart: ShoppingCart) {

            binding.apply {

                Log.d(TAG, "bindInfo: $shoppingCart")
                
                textListCartItemName.text = shoppingCart.cartName
                textCartCnt.text = shoppingCart.cartCnt.toString()
                textListCartItemPrice.text = "${makeComma(shoppingCart.cartPrice * shoppingCart.cartCnt)}"

                Glide.with(imageListCartItem.context)
                    .load("${SERVER_URL}images/${shoppingCart.cartImg}")
                    .into(imageListCartItem)


                // 삭제 버튼
                deleteListCartItem.setOnClickListener {
                    cartDeleteListener.onClick(position, shoppingCart)
                }

                // 수량 + 버튼
                imageCartPlus.setOnClickListener {
                    cartPlusListener.onPlus(shoppingCart.cartName)
                }

                // 수량 - 버튼
                imageCartMinus.setOnClickListener {
                    cartMinusListener.onMinus(shoppingCart.cartName)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ListCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindInfo(shoppingList[position])
    }
}