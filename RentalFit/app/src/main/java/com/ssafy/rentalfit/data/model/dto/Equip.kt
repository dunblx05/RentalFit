package com.ssafy.rentalfit.data.model.dto

data class Equip(
    val equipId: Int,
    val equipName: String,
    val equipType: String,
    val equipPrice: Int,
    val equipDetail: Any,
    val equipImg: String
)