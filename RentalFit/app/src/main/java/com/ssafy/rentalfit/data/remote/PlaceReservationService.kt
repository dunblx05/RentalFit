package com.ssafy.rentalfit.data.remote

import com.ssafy.rentalfit.data.model.response.PlaceReservationResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceReservationService {

    // 로그인한 사용자의 장소 예약 정보 조회
    @GET("rest/place/reservation/byUserId/{userId}")
    suspend fun selectPlaceResByUid(@Path("userId") userId : String) : List<PlaceReservationResponse>

    @GET("rest/place/reservation/byResId/{resId}")
    suspend fun selectPlaceResByRid(@Path("resId") resId : Int) : PlaceReservationResponse

}