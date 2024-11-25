package com.ssafy.rentalfit.ui.equip

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.rentalfit.data.model.dto.Equip
import com.ssafy.rentalfit.data.remote.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "EquipViewModel_싸피"
class EquipViewModel:ViewModel() {

    private val homeService = RetrofitUtil.homeService

    private val _equipList = MutableLiveData<List<Equip>>()
    val equipList: LiveData<List<Equip>> get() = _equipList

    private val _equip = MutableLiveData<Equip>()
    val equip: LiveData<Equip> get() = _equip

    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> get() = _quantity

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


    // 장바구니에 추가
    fun addToCart(equip: Equip) {

        val currentQuantity = _quantity.value ?: 1


    }
}