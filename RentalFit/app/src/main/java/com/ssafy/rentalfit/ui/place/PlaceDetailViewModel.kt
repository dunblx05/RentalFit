package com.ssafy.rentalfit.ui.place

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.rentalfit.data.remote.RetrofitUtil
import com.ssafy.rentalfit.data.model.dto.Place
import kotlinx.coroutines.launch

private const val TAG = "PlaceDetailViewModel_싸피"
class PlaceDetailViewModel : ViewModel() {
    private val homeService = RetrofitUtil.homeService

    private val _placeDetail = MutableLiveData<Place>()
    val placeDetail: LiveData<Place>
        get() = _placeDetail

    fun selectPlaceDetail(placeId : Int) {
        viewModelScope.launch {
            runCatching {
                homeService.selectPlaceById(placeId)
            }.onSuccess {
                Log.d(TAG, "selectPlaceDetail: $it")
                _placeDetail.value = it
            }.onFailure {
                Log.d(TAG, "selectPlaceDetail: ")
            }
        }
    }
}