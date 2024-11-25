package com.ssafy.rentalfit.ui.place

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.rentalfit.data.model.dto.Place
import com.ssafy.rentalfit.data.remote.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "PlaceViewModel_싸피"

class PlaceViewModel: ViewModel() {

    private val homeService = RetrofitUtil.homeService

    private val _placeList = MutableLiveData<List<Place>>()
    val placeList: LiveData<List<Place>> get() = _placeList


    // 전체 가져오기
    fun selectPlace() {
        viewModelScope.launch {
            runCatching {
                homeService.selectPlace()
            }.onSuccess {
                _placeList.value = it
            }.onFailure {
                Log.d(TAG, "selectPlace: 통신 실패")
            }
        }
    }
}