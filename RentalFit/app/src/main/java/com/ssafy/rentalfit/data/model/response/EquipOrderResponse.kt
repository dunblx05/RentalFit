package com.ssafy.rentalfit.data.model.response

data class EquipOrderResponse(
    val equipOrderId: Int,
    val userId: String,
    val equipOrderTime: String,
    val details: List<EquipOrderDetailResponse>
)