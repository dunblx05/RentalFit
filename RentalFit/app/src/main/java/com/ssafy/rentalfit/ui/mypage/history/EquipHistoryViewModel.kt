package com.ssafy.rentalfit.ui.mypage.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.rentalfit.data.model.response.EquipOrderResponse
import com.ssafy.rentalfit.data.model.response.EquipOrderWithInfoResponse
import com.ssafy.rentalfit.data.remote.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "EquipHistoryViewModel_싸피"

class EquipHistoryViewModel : ViewModel() {
    private val equipOrderService = RetrofitUtil.equipOrderService

    private val _equipOrderList = MutableLiveData<List<EquipOrderResponse>>()
    val equipOrderList: LiveData<List<EquipOrderResponse>>
        get() = _equipOrderList

    private val _equipOrderDetailList = MutableLiveData<List<EquipOrderWithInfoResponse>>()
    val equipOrderDetailList: LiveData<List<EquipOrderWithInfoResponse>>
        get() = _equipOrderDetailList

    fun selectEquipOrderByUid(userId: String) {
        viewModelScope.launch {
            runCatching {
                equipOrderService.selectEquipOrderByUid(userId)
            }.onSuccess {
                _equipOrderList.value = it
            }.onFailure {
                Log.d(TAG, "selectEquipOrderByUid: ")
            }
        }
    }

    fun selectEquipOrderByOrderId(equipOrderId: Int) {
        viewModelScope.launch {
            runCatching {
                equipOrderService.selectEquipOrderByOid(equipOrderId)
            }.onSuccess {
                _equipOrderDetailList.value = it
            }.onFailure {
                Log.d(TAG, "selectEquipOrderByOrderId: ")
            }
        }
    }
}