package com.ssafy.rentalfit.data.model.dto

data class EquipOrder(
    val equipOrderId: Int,
    val userId: String,
    val equipOrderTime: String,
    val details: List<EquipOrderDetail>
)