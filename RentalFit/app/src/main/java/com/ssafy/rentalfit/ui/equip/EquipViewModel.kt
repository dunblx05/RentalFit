package com.ssafy.rentalfit.ui.equip

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.ssafy.rentalfit.base.ApplicationClass
import com.ssafy.rentalfit.data.model.dto.Equip
import com.ssafy.rentalfit.data.model.dto.EquipOrder
import com.ssafy.rentalfit.data.model.dto.EquipOrderDetail
import com.ssafy.rentalfit.data.model.dto.ShoppingCart
import com.ssafy.rentalfit.data.remote.RetrofitUtil
import com.ssafy.rentalfit.util.ShoppingRepository
import kotlinx.coroutines.launch

private const val TAG = "EquipViewModel_싸피"
class EquipViewModel:ViewModel() {

    private val homeService = RetrofitUtil.homeService

    // 장비 리스트
    private val _equipList = MutableLiveData<List<Equip>>()
    val equipList: LiveData<List<Equip>> get() = _equipList

    // 장비 객체
    private val _equip = MutableLiveData<Equip>()
    val equip: LiveData<Equip> get() = _equip

    // 수량
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> get() = _quantity

    // 장바구니 리스트
//    private val _shoppingList = MutableLiveData<MutableList<ShoppingCart>>()
//    val shoppingList: LiveData<MutableList<ShoppingCart>> get() = _shoppingList
    val shoppingList: LiveData<MutableList<ShoppingCart>> = ShoppingRepository.shoppingList

    // 장바구니 리스트 개수
    val totalCnt: LiveData<Int> = ShoppingRepository.totalCnt

    // 장바구니 리스트의 총 가격
    val totalPrice: LiveData<Int> = ShoppingRepository.totalPrice

    // 전체 가져오기
    fun selectEquip() {
        viewModelScope.launch {
            runCatching {
                homeService.selectEquip()
            }.onSuccess {
                _equipList.value = it
            }.onFailure {
                Log.d(TAG, "selectEquip: 통신 실패")
            }
        }
    }

    // id로 가져오기
    fun selectEquipById(equipId: Int) {
        viewModelScope.launch {
            runCatching {
                homeService.selectEquipById(equipId)
            }.onSuccess {
                _equip.value = it
            }.onFailure {
                Log.d(TAG, "selectEquipById: 통신 실패")
            }
        }
    }

//    // 타입별로 가져오기.
//    fun selectEquipByType(equipType: String) {
//        viewModelScope.launch {
//            runCatching {
//                homeService.selectEquipByType(equipType)
//            }.onSuccess {
//                _equipList.value = it
//            }.onFailure {
//                Log.d(TAG, "selectEquipByType: 통신 실패")
//            }
//        }
//    }

    // 수량 증가
    fun plusQuantity() {
        val current = _quantity.value ?: 1
        _quantity.value = current + 1
    }

    // 수량 감소
    fun minusQuantity() {
        val current = _quantity.value ?: 1

        if(current > 1) {
            _quantity.value = current - 1
        }
    }

    fun addShoppingList(shoppingCart: ShoppingCart){
        ShoppingRepository.addShoppingList(shoppingCart)
    }

    // 총 수량과 가격 계산
    fun calculateCntPrice() {
        ShoppingRepository.calculateCntPrice()
    }

    // 장바구니 리스트에서 제거.
    fun removeItemShoppingList(shoppingCart: ShoppingCart) {
        ShoppingRepository.removeItemShoppingList(shoppingCart)
    }

    // 장바구니 리스트 초기화.
    fun clearShoppingList() {
        ShoppingRepository.clearShoppingList()
    }

    // 장바구니 수량 증가.
    fun increaseQuantity(cartName: String) {
        ShoppingRepository.increaseQuantity(cartName)
    }

    // 장바구니 수량 감소
    fun decreaseQuantity(cartName: String) {
        ShoppingRepository.decreaseQuantity(cartName)
    }

    fun makeEquipOrder() {

        val currentList = ShoppingRepository.shoppingList.value ?: mutableListOf()

        val equipOrderDetails = currentList.map { shoppingCart ->

            EquipOrderDetail(
                equipId = shoppingCart.cartId,
                quantity = shoppingCart.cartCnt,
            )
        } as ArrayList<EquipOrderDetail>

        val equipOrder = EquipOrder(
            equipOrderId = 0,
            userId = ApplicationClass.sharedPreferencesUtil.getUser().userId,
            equipOrderTime = "",
            details = equipOrderDetails
        )

        viewModelScope.launch {
            runCatching {
                RetrofitUtil.equipOrderService.makeOrder(equipOrder)
            }.onSuccess {

            }.onFailure {

            }
        }
    }
}