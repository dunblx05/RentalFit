package com.ssafy.rentalfit.data.remote

import com.ssafy.rentalfit.data.model.dto.EquipOrder
import com.ssafy.rentalfit.data.model.response.EquipOrderResponse
import com.ssafy.rentalfit.data.model.response.EquipOrderWithInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EquipOrderService {

    // 로그인한 사용자의 장비 주문 정보 조회
    @GET("rest/equip/order/byUserId/{userId}")
    suspend fun selectEquipOrderByUid(@Path("userId") userId: String) : List<EquipOrderResponse>

    // equipOrderId에 따른 주문 정보 조회
    @GET("rest/equip/order/byOrderId/{equipOrderId}")
    suspend fun selectEquipOrderByOid(@Path("equipOrderId") equipOrderId : Int) : List<EquipOrderWithInfoResponse>

    // 장비 구매(주문) 추가
    @POST("rest/equip/order")
    suspend fun makeOrder(@Body body: EquipOrder): Int
}