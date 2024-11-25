package com.ssafy.rentalfit.data.remote

import com.ssafy.rentalfit.data.model.response.PlaceReservationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceReservationService {

    // 로그인한 사용자의 장소 예약 정보 조회
    @GET("rest/place/reservation/byUserId/{userId}")
    suspend fun selectPlaceResByUid(@Path("userId") userId : String) : List<PlaceReservationResponse>

    @GET("rest/place/reservation/byResId/{resId}")
    suspend fun selectPlaceResByRid(@Path("resId") resId : Int) : PlaceReservationResponse

    // 새로운 장소 예약 생성
    @POST("rest/place/reservation")
    suspend fun insertPlaceReservation(@Body reservation: PlaceReservationResponse): Int

    // 오늘 날짜 기준으로 특정 장소의 예약 목록 조회
    @GET("rest/place/reservation/InToday/{placeId}")
    suspend fun selectResByPidInToday(@Path("placeId") placeId: Int): List<PlaceReservationResponse>

    // 특정 날짜 기준으로 특정 장소의 예약 목록 조회
    @GET("rest/place/reservation/InDate/{placeId}")
    suspend fun selectResByPidInDate(
        @Path("placeId") placeId: Int,
        @Query("date") date: String // "yyyy-MM-dd" 형식
    ): List<PlaceReservationResponse>
}