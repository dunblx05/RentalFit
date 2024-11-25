package com.ssafy.rentalfit.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssafy.rentalfit.base.ApplicationClass
import com.ssafy.rentalfit.data.model.dto.EquipOrder
import com.ssafy.rentalfit.data.model.dto.EquipOrderDetail
import com.ssafy.rentalfit.data.model.dto.ShoppingCart
import com.ssafy.rentalfit.data.remote.RetrofitUtil
import kotlinx.coroutines.launch

object ShoppingRepository {

    // 장바구니 리스트
    private val _shoppingList = MutableLiveData<MutableList<ShoppingCart>>()
    val shoppingList: LiveData<MutableList<ShoppingCart>> get() = _shoppingList

    // 장바구니 리스트 개수
    private val _totalCnt = MutableLiveData<Int>()
    val totalCnt: LiveData<Int> get() = _totalCnt

    // 장바구니 리스트의 총 가격
    private val _totalPrice = MutableLiveData<Int>()
    val totalPrice: LiveData<Int> get() = _totalPrice

    fun addShoppingList(shoppingCart: ShoppingCart){

        val currentList = _shoppingList.value ?: mutableListOf()

        val position = checkDuplication(shoppingCart.cartName)

        if (position == -1) {
            currentList.add(shoppingCart)
        }
        else {
            currentList[position].addDupCart(shoppingCart)
        }

        _shoppingList.value = currentList
    }

    private fun checkDuplication(itemName: String): Int {

        val currentList = _shoppingList.value ?: mutableListOf()

        var position = -1

        currentList.forEachIndexed { index, item ->
            if (item.cartName == itemName)
                position = index
        }
        return position
    }

    // 총 수량과 가격 계산
    fun calculateCntPrice() {

        var count = 0
        var price = 0

        val currentList = _shoppingList.value ?: mutableListOf()

        currentList.forEach {
            count += it.cartCnt
            price += it.cartCnt * it.cartPrice
        }

        _totalCnt.value = count
        _totalPrice.value = price
    }

    // 장바구니 리스트에서 제거.
    fun removeItemShoppingList(shoppingCart: ShoppingCart) {
        _shoppingList.value!!.remove(shoppingCart)
        calculateCntPrice()
    }

    // 장바구니 리스트 초기화.
    fun clearShoppingList() {
        _shoppingList.value = mutableListOf()
        _totalCnt.value = 0 // 총 수량 초기화
        _totalPrice.value = 0 // 총 가격 초기화
    }

    // 수량 증가
    fun increaseQuantity(cartName: String) {
        val currentList = _shoppingList.value ?: mutableListOf()
        val position = checkDuplication(cartName)
        if (position != -1) {
            currentList[position].cartCnt += 1
            _shoppingList.value = currentList
            calculateCntPrice()
        }
    }

    // 수량 감소
    fun decreaseQuantity(cartName: String) {
        val currentList = _shoppingList.value ?: mutableListOf()
        val position = checkDuplication(cartName)
        if (position != -1) {
            if (currentList[position].cartCnt > 1) {
                currentList[position].cartCnt -= 1
            } else {
                currentList.removeAt(position)
            }
            _shoppingList.value = currentList
            calculateCntPrice()
        }
    }

//    // 서버에 주문 넣기.
//    suspend fun makeEquipOrder(equipOrder: EquipOrder) {
//        RetrofitUtil.equipOrderService.makeOrder(equipOrder)
//    }

}