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
}