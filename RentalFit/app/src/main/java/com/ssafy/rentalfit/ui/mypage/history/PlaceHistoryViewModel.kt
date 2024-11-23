package com.ssafy.rentalfit.ui.mypage.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.rentalfit.data.model.response.PlaceReservationResponse
import com.ssafy.rentalfit.data.remote.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "PlaceHistoryViewModel_싸피"

class PlaceHistoryViewModel : ViewModel() {

    private val placeReservationService = RetrofitUtil.placeReservationService

    private val _placeReservationList = MutableLiveData<List<PlaceReservationResponse>>()
    val placeReservationList: LiveData<List<PlaceReservationResponse>>
        get() = _placeReservationList

    private val _placeResDetail = MutableLiveData<PlaceReservationResponse>()
    val placeResDetail: LiveData<PlaceReservationResponse>
        get() = _placeResDetail


    fun selectPlaceReservationByUid(userId: String) {
        viewModelScope.launch {
            runCatching {
                placeReservationService.selectPlaceResByUid(userId)
            }.onSuccess {
                _placeReservationList.value = it
            }.onFailure {
                Log.d(TAG, "selectPlaceReservationByUid: ")
            }
        }
    }

    fun selectPlaceResByRid(placeId: Int) {
        viewModelScope.launch {
            runCatching {
                placeReservationService.selectPlaceResByRid(placeId)
            }.onSuccess {
                _placeResDetail.value = it
            }.onFailure {
                Log.d(TAG, "selectPlaceByPid: ")
            }
        }
    }

}