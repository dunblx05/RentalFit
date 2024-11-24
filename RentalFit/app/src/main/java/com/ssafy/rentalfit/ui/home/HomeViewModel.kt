package com.ssafy.rentalfit.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.rentalfit.data.model.dto.Equip
import com.ssafy.rentalfit.data.remote.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel_싸피"
class HomeViewModel : ViewModel() {

    private val homeService = RetrofitUtil.homeService

    private val _equipList = MutableLiveData<List<Equip>>()
    val equipList: LiveData<List<Equip>>
        get() = _equipList
    
    fun selectEquip() {
        viewModelScope.launch { 
            runCatching { 
                homeService.selectEquip()
            }.onSuccess { 
                _equipList.value = it
            }.onFailure {
                Log.d(TAG, "selectEquip: ")
            }
        }
    }

}