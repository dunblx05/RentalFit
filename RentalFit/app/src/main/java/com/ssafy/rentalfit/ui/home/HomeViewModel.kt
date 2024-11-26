package com.ssafy.rentalfit.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.rentalfit.data.model.dto.Equip
import com.ssafy.rentalfit.data.model.dto.Place
import com.ssafy.rentalfit.data.remote.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel_싸피"
class HomeViewModel : ViewModel() {

    private val homeService = RetrofitUtil.homeService
    private val firebaseTokenService = RetrofitUtil.firebaseTokenService

    private val _equipList = MutableLiveData<List<Equip>>()
    val equipList: LiveData<List<Equip>>
        get() = _equipList

    private val _placeList = MutableLiveData<List<Place>>()
    val placeList: LiveData<List<Place>>
        get() = _placeList

    private val _nfcTagValue = MutableLiveData<String>()
    val nfcTagValue: LiveData<String> get() = _nfcTagValue

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

    fun selectPlace() {
        viewModelScope.launch {
            runCatching {
                homeService.selectPlace()
            }.onSuccess {
                _placeList.value = it
            }.onFailure {
                Log.d(TAG, "selectPlace: ")
            }
        }
    }

    // NFC 태그 값 설정 함수
    fun setNfcTagValue(tagValue: String) {
        _nfcTagValue.value = tagValue
    }

    fun resetNfcTagValue() {
        _nfcTagValue.value = ""
    }

    fun startNfc(token: String) {

        Log.d(TAG, "startNfc: $token")

        viewModelScope.launch {
            runCatching {
                firebaseTokenService.sendMessageTo(token, "장소 대여 시작", "장소 대여가 시작되었습니다.")
            }
        }
    }

    fun finishNfc(token: String) {

        viewModelScope.launch {
            runCatching {
                firebaseTokenService.sendMessageTo(token, "장소 대여 종료", "장소 대여가 종료되었습니다.")
            }
        }
    }
}