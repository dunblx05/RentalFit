package com.ssafy.rentalfit.data.model.response

import com.ssafy.rentalfit.data.model.dto.Equip

data class EquipOrderWithInfo(
    val detailId: Int,
    val equipOrderId: Int,
    val equipId: Int,
    val quantity: Int,
    val equip: Equip
)