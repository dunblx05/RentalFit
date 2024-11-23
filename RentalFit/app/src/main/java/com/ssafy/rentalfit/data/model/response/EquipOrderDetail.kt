package com.ssafy.rentalfit.data.model.response

data class EquipOrderDetail(
    val detailId: Int,
    val equipOrderId: Int,
    val equipId: Int,
    val quantity: Int,
    val equipName: String,
    val equipPrice: Int,
    val equipImg: String
)