package com.ssafy.rentalfit.data.model.dto

data class ShoppingCart(
    val cartId: Int,
    val cartImg: String,
    val cartName: String,
    var cartCnt: Int,
    val cartPrice: Int,
    var cartTotalPrice: Int = cartPrice * cartCnt
) {

    fun addDupCart(shoppingCart: ShoppingCart) {
        this.cartCnt += shoppingCart.cartCnt
        this.cartTotalPrice = this.cartCnt * this.cartPrice
    }

    fun subtractDupCart(shoppingCart: ShoppingCart) {
        this.cartCnt = if (this.cartCnt > shoppingCart.cartCnt) this.cartCnt - shoppingCart.cartCnt else 0
    }
}