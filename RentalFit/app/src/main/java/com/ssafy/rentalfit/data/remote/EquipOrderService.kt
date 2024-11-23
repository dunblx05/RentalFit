package com.ssafy.rentalfit.data.remote

import com.ssafy.rentalfit.data.model.response.EquipOrderResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EquipOrderService {

    // 로그인한 사용자의 장비 주문 정보 조회
    @GET("rest/equip/order/byUserId/{userId}")
    suspend fun selectEquipOrderByUid(@Path("userId") userId: String) : List<EquipOrderResponse>
}